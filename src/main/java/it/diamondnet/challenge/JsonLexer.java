package it.diamondnet.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonLexer extends Lexer {   
	public static final int LBRACE = 2;
    public static final int RBRACE = 3;
	public static final int COMMA  = 4;
	public static final int COLON  = 5;
	public static final int DQUOTE = 6;
	public static final int ALPHA =  7;
	public static final int BOOL  =  8;
	public static final int NUMBER = 9;
    public static final int NULL   = 10;
	public static final int LBRACK = 11;
	public static final int RBRACK = 12;
    public static final int STRING = 13;
	
    public static final char LBRAC = '{';
    public static final char RBRAC = '}';
	public static final char COMMAC = ',';
	public static final char COLONC	= ':';
	public static final char DQUOTEC = '"';
	public static final char TRUEC = 't';
	public static final char FALSEC = 'f';
	public static final char NULLC = 'n';
	public static final char LBRACKC = '[';
	public static final char RBRACKC = ']';
	public static final char MINUSC = '-';
	public static final char DOTC = '.';
	public static final char BACKSC = '\\';

	public static final String	TRUES = "true";
	public static final String	FALSES = "false";
    public static final String	NULLS = "null";
	public static final String  EQUOTES = "\\\"";

    public static String[] tokenNames = {"n/a", "<EOF>", "LBRACE", "RBRACE", "COMMA", "COLON", "DQUOTE", "ALPHA", "BOOL", "NUMBER", "NULL", "LBRACK", "RBRACK", "STRING"};
    private static final String REGEX_JSON_NUMBER = "^-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?$";
  
	public JsonLexer(String input) {
        super(input);
        
    }

    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ': case '\n': case '\t': case '\r': WS(); // skip whitespace
                    continue; // go to next iteration of the loop 
                case LBRAC:
                    consume();
                    return new Token(LBRACE, "{");
                case RBRAC:
                    consume();
                    return new Token(RBRACE, "}");
				case DQUOTEC:
					return STRING();
				case COLONC:
					consume();
					return new Token(COLON, ":");
				case COMMAC:
                    consume();
					return new Token(COMMA, ",");	
				case TRUEC:	// true start char
					if (matchPattern(TRUES))
						return new Token(BOOL, TRUES);
					else {
						consume(); 
					    return new Token(ALPHA, Character.toString(c));
					}
				case FALSEC: // false start char
					if (matchPattern(FALSES))
						return new Token(BOOL, FALSES);
					else {
						consume(); 
				    	return new Token(ALPHA, Character.toString(c));
					}
				case NULLC: // null start char
					if (matchPattern(NULLS))
						return new Token(NULL, NULLS);
					else {
						consume(); 
				    	return new Token(ALPHA, Character.toString(c));
					} //	
				case LBRACKC:
                    consume();
					return new Token(LBRACK, "[");
				case RBRACKC:
                    consume();
					return new Token(RBRACK, "]");		
				case MINUSC: {
					return NUMBER();
					}
				case DOTC: {
					int initialLoc = p;
					try { if (Character.isDigit(input.charAt(initialLoc+1))) 
						{ consume(); return new Token(NUMBER, Character.toString(c)); } 
					}
					catch (IndexOutOfBoundsException e) { break;}	
				}
                default:
					return manage_default();
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }
   
	private Token manage_default() {
		if (Character.isDigit(c) || c == MINUSC ) { 
			return NUMBER(); 
		}
		else {
			System.err.println(print_debug());			
			throw new Error("Invalid character >>>" + c + "<<< found at location " + p);
		}
	}

    protected Token STRING() {
        StringBuilder buf = new StringBuilder();
        consume(); // consume the opening quote
        while (c != DQUOTEC) {
            if (c == BACKSC) {
                consume(); // consume the backslash
                switch (c) {
                    case '"':
                        buf.append('"');
                        break;
                    case '\\':
                        buf.append('\\');
                        break;
                    case '/':
                        buf.append('/');
                        break;
                    case 'b':
                        buf.append('\b');
                        break;
                    case 'f':
                        buf.append('\f');
                        break;
                    case 'n':
                        buf.append('\n');
                        break;
                    case 'r':
                        buf.append('\r');
                        break;
                    case 't':
                        buf.append('\t');
                        break;
                    case 'u':
                        StringBuilder hex = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            consume();
                            hex.append(c);
                        }
                        try {
                            int unicodeValue = Integer.parseInt(hex.toString(), 16);
                            buf.append((char) unicodeValue);
                        } catch (NumberFormatException e) {
                            throw new Error("Invalid unicode escape sequence: \\u" + hex.toString());
                        }
                        break;
                    default:	
                        throw new Error("Invalid escape sequence: \\" + c);
                }
            } else {
                buf.append(c);
            }
            consume();
        }
        consume(); // consume the closing quote
        return new Token(STRING, buf.toString());
    }

    protected void WS() {
        while (c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == '\f')
            consume();
    }

    protected Token ALPHA() {
    	StringBuilder buf = new StringBuilder(); 
        do { buf.append(c); consume(); } while (isAlphaValid(c) && c != EOF); 
		Token tok = new Token(ALPHA, buf.toString());
		return tok;
    }
    
    protected boolean isValidJsonNumber(String text) {
        // 1. Controlla che l'input non sia vuoto o nullo
        if (text == null || text.length() == 0) {
            return false;
        }
        System.out.println(text);
  
        // 2. Esegui il match con la regex
        //    Il metodo .matches() controlla che l'intera stringa corrisponda
        boolean is_match = text.matches(REGEX_JSON_NUMBER);

        // 3. Restituisci il risultato
        return is_match;
        }

    protected Token NUMBER() {
        StringBuilder buf = new StringBuilder();
        buf.append(c);
        consume();
        while (c != RBRAC && c != RBRACKC && c != COMMAC && c != ' ' && c != EOF && c != '\n' && c != '\t' && c != '\r' && c != '\f') {
            buf.append(c);
            consume();
        }

        if (isValidJsonNumber(buf.toString()))
            return new Token(NUMBER, buf.toString());
        else
            throw new Error("Invalid number sequence (length = " + buf.toString().length() + " >> : " + buf.toString());
    }

    
    public String getTokenName(int type) {
        return tokenNames[type];
    }

    protected static String leggiJsonDaFile(String percorso) throws IOException {
        return new String(Files.readAllBytes(Paths.get(percorso)));
    }
    
	protected boolean isAlphaValid(char c) {
        return c != '"' && c >= 0x20;
	}
	
    private String print_debug() {
        int initial; int end; int p = getP() -1; int l = input.length(); int delta = 20;
        
        if ( c == EOF )
            end= l -1;
        if ((p - delta) >= 0)
            initial = p - delta;
        else 
            initial = 0;
        if ((p + delta) < l)
            end = p + delta;
        else 
            end = l -1;
        StringBuilder buf = new StringBuilder();
        for (int i = initial; i < p; i++) {
            if ((i < 0) || (i >= l))
                break;
            buf.append( input.charAt(i));
        }
        buf.append('<');
        if ((p >= 0) && (p < l))
            buf.append( input.charAt(p));
        buf.append('>');
        for (int i = p + 1; i < end; i++) {
            if ((i < 0) || (i >= l))
                break;
            buf.append( input.charAt(i));
        }
        return(buf.toString());
    }

}
