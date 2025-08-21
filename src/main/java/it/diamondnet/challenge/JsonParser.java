package it.diamondnet.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser extends Parser {   

    public JsonParser(Lexer input) {
        super(input);        
    }


    // JSON Object (see grammar)
    public JsonObjectNode jObject() {
        JsonObjectNode objectNode = new JsonObjectNode();
        // each case checks for opening, whatever the value is
        if (!match(JsonLexer.LBRACE)) {
            System.err.println(print_debug());
            throw new Error("Expecting opening object {, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }

       // Empty
       if (getLookAhead().type == JsonLexer.RBRACE) {
          match(JsonLexer.RBRACE);
          return objectNode;
        }
        
        while (true) {
            String key = jString().getValue();	

            // kvalue or empty or string:value
            if (!match(JsonLexer.COLON)) {
                System.err.println(print_debug());
                throw new Error("Expecting colon in object :, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
            }
            JsonNode value = jValue();
            objectNode.put(key, value);

            // other key/value
            if (getLookAhead().type == JsonLexer.COMMA) {
                match(JsonLexer.COMMA);
                continue;
            }

            // end of object (no comma)
            else if (getLookAhead().type == JsonLexer.RBRACE) {
                match(JsonLexer.RBRACE);	   
                break;
            }
            
            // no parsing
            else  {
                System.err.println(print_debug());
                throw new Error("Expecting comma or closing brace in object :, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
            }
        }
        return objectNode;  
    } // jObject()
    
    // JSON Value
    public JsonNode jValue() {       
        if (getLookAhead().type == JsonLexer.STRING) {
            return jString();
        }
      
        else if (getLookAhead().type == JsonLexer.NUMBER) {
            // number value
            return jNumber();
        }

        else if (getLookAhead().type == JsonLexer.LBRACE) {
            // jobject as nested value
            return jObject();
        }
        
        else if (getLookAhead().type == JsonLexer.NULL) {
            // null value
            return jNull();
        }
        
        else if (getLookAhead().type == JsonLexer.BOOL) {
            return jBool();
        }
          
        else if (getLookAhead().type == JsonLexer.LBRACK) {
            // array of values, is a value itself
            return jArray();
        }
        else {
            System.err.println(print_debug());
            throw new Error("Expecting value in object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
    }   
    
    // JSON Value: String
    public JsonStringNode jString() {  
        Token t = getLookAhead();
        match(JsonLexer.STRING);
        return new JsonStringNode(t.text);
    }
    
    // JSON Value: Bool
    public JsonBooleanNode jBool() {
        Token t = getLookAhead();
        if (!match(JsonLexer.BOOL)) {
            System.err.println(print_debug());
            throw new Error("Expecting bool in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
        if (t.text.equals("true"))
            return new JsonBooleanNode(true);
        return new JsonBooleanNode(false); 
    }
    
    // JSON Value: Null
    public JsonNullNode jNull() {
        if (!match(JsonLexer.NULL)) {
            System.err.println(print_debug());
            throw new Error("Expecting null in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
        return JsonNullNode.getInstance();

    }

    // JSON Value: Number
    public JsonNumberNode jNumber() {
        Token t = getLookAhead();
        if (!match(JsonLexer.NUMBER)) {
            System.err.println(print_debug());
            throw new Error("Expecting number in value of object, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
        while (match(JsonLexer.NUMBER)) {	
            ;
        }
        return new JsonNumberNode(t.text);
    }	

    // JSON Value: Array (of value or object)
    public JsonArrayNode jArray() {
        JsonArrayNode arrayNodes = new JsonArrayNode();
       
        // each case checks for opening, whatever the value is
        if (!match(JsonLexer.LBRACK)) {
            System.err.println(print_debug());
            throw new Error("Expecting opening object [, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }

       // Empty
       if (getLookAhead().type == JsonLexer.RBRACK) {
          match(JsonLexer.RBRACK);
          return arrayNodes;
        }   

        while (true) {
            // value
            arrayNodes.add(jValue());	

            // other value
            if (getLookAhead().type == JsonLexer.COMMA) {
                match(JsonLexer.COMMA);
                continue;
            }

            // end of array (no comma)
            else if (getLookAhead().type == JsonLexer.RBRACK) {
                match(JsonLexer.RBRACK);	   
                break;
            }
            
            // no parsing
            else  {
                System.err.println(print_debug());
                throw new Error("Expecting comma or closing brace in array :, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
            }
        } // while
        return arrayNodes;
    } // jArray()
    
    // Json payload (object or array)
    public JsonNode jPayload() {
        JsonNode payloadNode;
        if (getLookAhead().type == JsonLexer.LBRACE) {
            payloadNode = jObject();
        }
        
        else if (getLookAhead().type == JsonLexer.LBRACK) {
            payloadNode = jArray();
        }

        else {
         System.err.println(print_debug());
            throw new Error("Expecting opening payload JSON (object or array) {, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);            
        }
        if (getLookAhead().type != Lexer.EOF_TYPE) {
            System.err.println(print_debug());
            throw new Error("Expecting end of file after payload, found " + getLookAhead().type + " " + getLookAhead().text + " at location: " + getInput().p);
        }
        return payloadNode;

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
        for (int i = initial; i < p; i++) {
            if ((i < 0) || (i >= l))
                break;
            buf.append( getInput().input.charAt(i));
        }
        buf.append('<');
        if ((p >= 0) && (p < l))
            buf.append( getInput().input.charAt(p));
        buf.append('>');
        for (int i = p + 1; i < end; i++) {
            if ((i < 0) || (i >= l))
                break;
            buf.append( getInput().input.charAt(i));
        }
        return(buf.toString());
    }

}
