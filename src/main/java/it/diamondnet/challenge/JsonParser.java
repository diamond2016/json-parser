package it.diamondnet.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser extends Parser {   

    public JsonParser(Lexer input) {
        super(input);        
    }

    // JSON Object (see grammar)
    public void jObject() {
        // each case checks for opening, whatever the value is
        if (!match(JsonLexer.LBRACE)) {
            System.err.println(print_debug());
            throw new Error("Expecting opening object {, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }

        while (true) {
            // if the next token is not a string, then it's the end of the object
            if (getLookAhead().type != JsonLexer.DQUOTE) {
                break;
            }
            jString();	

            // kvalue or empty or string:value
            if (!match(JsonLexer.COLON)) {
                System.err.println(print_debug());
                throw new Error("Expecting colon in object :, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
            }
            jValue();
            
            if (getLookAhead().type != JsonLexer.RBRACE) {
                if (!match(JsonLexer.COMMA)) {
                    System.err.println(print_debug());
                    throw new Error("Expecting comma in object ,, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
                }

                if (getLookAhead().type != JsonLexer.DQUOTE) {
                    System.err.println(print_debug());
                    throw new Error("Expecting other key/value, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
                }
            }
            else
                break;
        }
        match(JsonLexer.RBRACE);	   
    }
    
    // JSON Value
    public void jValue() { 
        if (getLookAhead().type == JsonLexer.LBRACE) {
            // jobject as nested value
            jObject();
        }
        else if (getLookAhead().type == JsonLexer.NULL) {
            // null value
            jNull();
        }
        
        else if (getLookAhead().type == JsonLexer.BOOL) {
            jBool();
        }
        
        else if (getLookAhead().type == JsonLexer.NUMBER) {
            // number value
            jNumber();
        }
        
        else if (getLookAhead().type == JsonLexer.DQUOTE) {
            jString();
        }
        
        else if (getLookAhead().type == JsonLexer.LBRACK) {
            // array of values, is a value itself
            jArray();
        }
        else {
            System.err.println(print_debug());
            throw new Error("Expecting value in object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
    }   
    
    // JSON Value: String
    public void jString() {  
        match(JsonLexer.DQUOTE);
        // inside quotes, all allowed
        while (match(JsonLexer.ALPHA) || 
               match(JsonLexer.NUMBER) || 
               match(JsonLexer.COLON)  || 
               match(JsonLexer.COMMA)  ||
               match(JsonLexer.TRUEC)  || match(JsonLexer.FALSEC)  ||  
               match(JsonLexer.LBRACKC) || match(JsonLexer.RBRACKC)  || 
               match(JsonLexer.MINUSC) || match(JsonLexer.DOTC)
               ) { ; }
        match(JsonLexer.DQUOTE);
    }
    
    // JSON Value: Bool
    public void jBool() {
        if (!match(JsonLexer.BOOL)) {
            System.err.println(print_debug());
            throw new Error("Expecting bool in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
    }
    
    // JSON Value: Null
    public void jNull() {
        if (!match(JsonLexer.NULL)) {
            System.err.println(print_debug());
            throw new Error("Expecting null in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }

    }

    // JSON Value: Number
    public void jNumber() {
        if (!match(JsonLexer.NUMBER)) {
            System.err.println(print_debug());
            throw new Error("Expecting number in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
        while (match(JsonLexer.NUMBER)) {	
            ;
        }
    }	

    // JSON Value: Array (of value)
    public void jArray() {
        match(JsonLexer.LBRACK);
        while (getLookAhead().type != JsonLexer.RBRACK) {
            jValue();	
            if (getLookAhead().type == JsonLexer.RBRACK)
                break; // case of single value in array
            match(JsonLexer.COMMA);
        }
        match(JsonLexer.RBRACK); 
    }
    
    protected static String leggiJsonDaFile(String percorso) throws IOException {
        return new String(Files.readAllBytes(Paths.get(percorso)));
    }
    
    private String print_debug() {
        int initial; int end; int p = getInput().p -1; int l = getInput().input.length(); int delta = 100;
        if ( getInput().c == (char) -1 )
            return ("");
        if ((p - delta) >= 0)
            initial = p - delta;
        else 
            initial = 0;
        if ((p + delta) < l)
            end = p + delta;
        else 
            end = l -1;
        StringBuilder buf = new StringBuilder();
        for (int i = initial; i < p; i++)
            buf.append( getInput().input.charAt(i));
        buf.append('<');
        buf.append( getInput().input.charAt(p));
        buf.append('>');
        for (int i = p + 1; i < end; i++)
            buf.append( getInput().input.charAt(i));
        return(buf.toString());
    }

}
