package it.diamondnet.challenge;
public abstract class Lexer {
    public static final char EOF = (char) -1; // EOF for java read() in InputStream
    public static final int EOF_TYPE = 1; // token type
    String input;
    int p = 0; // current position in input
    char c; // current character
    
    public Lexer(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        this.input = input;
        c = input.charAt(p);
    }

    // move to next character, detects EOF
    public void consume() {
        p++;
        if (p >= input.length()) {
            c = EOF;
        } else {
            c = input.charAt(p);
        }
    }
    
    // if current character matches x consumes it, otherwise throws an error
    public void match(char x) {
        if (c == x) {
            consume();
        } else {
            throw new Error("Expecting " + x + "; found " + c + " at location " + p);
        }
    }

    
    // to validate a pattern of chars not a single char
    public boolean matchPattern(String pattern) {
    	int initialLoc = p;
    	int i = 0;

    	while (i < pattern.length() && lookForwardChar(pattern.charAt(i))) {
    		consume();
    		i++;
        }
    	if (i == pattern.length()) 
    		return true;
    	
    	setP(initialLoc);
    	return false;
    }
    
    // location 
    public int getP() {
    	return this.p;    
    }
    public void setP(int location) {
    	this.p = location;    
    }
    
    public abstract Token nextToken();
    public abstract String getTokenName(int tokenType);


	// move to next character, detects EOF, checks input,
	protected boolean lookForwardChar(char nextc) {
		if ((c != EOF) && (c == nextc))
				return true;
  
		return false;
	}    
}

