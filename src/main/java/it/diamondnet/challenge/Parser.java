package it.diamondnet.challenge;
public abstract class Parser {
    private Lexer input;
    private Token lookahead;

    public Parser(Lexer input) {
        if (input == null) {
            throw new IllegalArgumentException("Input Lexer cannot be null");
        }
  
        this.input = input;
        this.lookahead = input.nextToken(); // initialize lookahead
    }

    public Token getLookAhead() {
    	return lookahead;
    }
    public Lexer getInput() {
    	return input;
    }
    
    
    // move to next character, detects EOF
    public void consume() {
        if (lookahead.type != Lexer.EOF_TYPE) {
            lookahead = input.nextToken();
        } else {
            throw new Error("Unexpected end of input");
        }
    }
    

    // if current character matches type x consumes it, otherwise throws an error
    public boolean match(int x) {
        if (lookahead.type == x) {
            consume();
            return true;
        } else { 
        	System.out.println("Warning matching token type " + x +  " found token " + lookahead);
            return false;
        }
    }      
    
}

