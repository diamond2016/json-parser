package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/* token types
EOF = 1
LBRACE = 2;
RBRACE = 3;
COMMA  = 4;
COLON  = 5;
DQUOTE = 6;
ALPHA = 7;
BOOL   = 8;
NUMBER = 9;
*/
class JsonLexerStep3Test {

    @Test
    public void jsonLexerStep3MatchPatternTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/pattern.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test pattern");
        JsonLexer lexer = new JsonLexer(input);
        System.out.println(lexer.matchPattern("true"));
    }

    @Test
    public void jsonLexerStep3EOFTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/empty.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test EOF");
        JsonLexer lexer = new JsonLexer(input);
        assertEquals(lexer.nextToken().type, JsonLexer.EOF_TYPE);
    }

    @Test
    public void jsonLexerStep3LRBRACETest() {
        String input;
        String percorso = "src/test/resources/tests/step1/valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test LBRACE+RBRACEF");
        JsonLexer lexer = new JsonLexer(input);
        assertEquals(lexer.nextToken().type, JsonLexer.LBRACE);
        assertEquals(lexer.nextToken().type, JsonLexer.RBRACE);
    }

    @Test
    public void jsonLexerStep3COMMATest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validcomma.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test COMMA");
        JsonLexer lexer = new JsonLexer(input);
        assertEquals(lexer.nextToken().type, JsonLexer.COMMA);
    }    
    
    @Test
    public void jsonLexerStep3COLONTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validcolon.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test COLON");
        JsonLexer lexer = new JsonLexer(input);
        assertEquals(lexer.nextToken().type, JsonLexer.COLON);
    }
    
    @Test
    public void jsonLexerStep3DQUOTETest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validstring.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test DQUOTE");
        JsonLexer lexer = new JsonLexer(input);
        assertEquals(lexer.nextToken().type, JsonLexer.DQUOTE);
        assertEquals(lexer.nextToken().type, JsonLexer.DQUOTE);
    } 
    
    @Test
    public void jsonLexerStep3ALPHATest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validalpha.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test ALPHA");
        JsonLexer lexer = new JsonLexer(input);
        Token token = lexer.nextToken();
        System.out.println(token);
        assertEquals(JsonLexer.ALPHA, token.type);      
        assertEquals(token.text.length(), 1);
     
    }      

    @Test
    public void jsonLexerStep3BOOLTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validbool.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test BOOL");
        JsonLexer lexer = new JsonLexer(input);
        Token token = lexer.nextToken();
        assertEquals(token.type, JsonLexer.BOOL);      
        assertEquals(token.text.length(), "true".length());
    }

    @Test
    public void jsonLexerStep3NUMBERTest() {
        String input;
        // 123220
        String percorso = "src/test/resources/tests/step3/validnumber.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test NUMBER");
        JsonLexer lexer = new JsonLexer(input);
        System.out.println("c " + lexer.c);
        Token token = lexer.nextToken();    
        
        System.out.println("text " + token.text);
        System.out.println("type " + token.type);
        assertEquals(JsonLexer.NUMBER, token.type);      
        assertEquals(token.text, "1");     
    }  

    @Test
    public void jsonLexerStep3NULLTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/validnull.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test NULL");
        JsonLexer lexer = new JsonLexer(input);
        Token token = lexer.nextToken();
        assertEquals(token.type, JsonLexer.NULL);      
        assertEquals(token.text, "null");

    }   
    
    @Test
    public void jsonLexerStep3BoolInvalidTest() {
        String input;
        String percorso = "src/test/resources/tests/step3/invalidbool.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test invalid BOOL");
        JsonLexer lexer = new JsonLexer(input);
        Token token = lexer.nextToken();
        System.out.println(token);  
        assertNotEquals(token.type, JsonLexer.BOOL);    
    }


}
