package it.diamondnet.challenge;

public class Token {
    public int type; // token type
    public String text; // token text
    
    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }
                      
    @Override
    public String toString() {

        if (text == null) {
            text = "<no text>";
        }
  
        return "<" + text + ">," + " token type: " + type;
    }
}