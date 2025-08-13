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

	public static final String	TRUES = "true";
	public static final String	FALSES = "false";
    public static final String	NULLS = "null";

    public static String[] tokenNames = {"n/a", "<EOF>", "LBRACE", "RBRACE", "COMMA", "COLON", "DQUOTE", "ALPHA", "BOOL", "NUMBER", "NULL", "LBRACK", "RBRACK"};


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
					consume();
					return new Token(DQUOTE, "\"");
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
				}	
				case LBRACKC:
                    consume();
					return new Token(LBRACK, "[");
				case RBRACKC:
                    consume();
					return new Token(RBRACK, "]");		
				case MINUSC: {
					int initialLoc = p;
					try { if (Character.isDigit(input.charAt(initialLoc+1))) 
						{consume(); return new Token(NUMBER, Character.toString(c)); } 
					}
					catch (IndexOutOfBoundsException e) { break;}	
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
		char save_c = c;
		if (c == DQUOTEC) {
		    consume();
		    return new Token(DQUOTE, "\"");
		}
		else if (Character.isDigit(c)) { 
			consume(); 
			return new Token(NUMBER, Character.toString(save_c)); 
		}
		else if (isAlphaValid(c) || isBackSlash(c)) { 
			consume(); 
			return new Token(ALPHA, Character.toString(save_c)); 
		}
		else 
			throw new Error("Invalid character " + c + " found at location " + p);
	}

    protected void WS() {
        while (c == ' ' || c == '\n' || c == '\t' || c == '\r' || c== '\\')
            consume();
    }

    protected Token ALPHA() {
    	StringBuilder buf = new StringBuilder(); 
        do { buf.append(c); consume(); } while (isAlphaValid(c) && c != EOF); 
		Token tok = new Token(ALPHA, buf.toString());
		return tok;
    }
    
    protected Token NUMBER() {
    	StringBuilder buf = new StringBuilder(); 
        do { buf.append(c); consume(); } while ((Character.isDigit(c) || (c == '-') || (c == '.')) && c != EOF);
		Token tok = new Token(NUMBER, buf.toString());
		return tok;
    }
    
    public String getTokenName(int type) {
        return tokenNames[type];
    }

    protected static String leggiJsonDaFile(String percorso) throws IOException {
        return new String(Files.readAllBytes(Paths.get(percorso)));
    }
    
	protected boolean isAlphaValid(char c) {
        return c != '"' && (c != '\\') && c >= 0x20;
	}
	
	protected boolean isBackSlash(char c) {
		boolean isBacksl =  (c =='\\' && (p+1 < input.length()) && (input.charAt(p+1) == '\\'));
		if (isBacksl)
			consume();
		return isBacksl;
	}
	

}
