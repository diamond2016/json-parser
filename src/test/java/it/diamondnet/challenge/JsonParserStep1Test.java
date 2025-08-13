package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertThrows;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JsonParserStep1Test {

    @Test
    public void jsonLexerTestT1() {
        String input;
        String percorso = "src/test/resources/t1.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("T1");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        parser.jObject();
    }
 
    @Test
    public void jsonLexerTestT2() {
        String input;
        String percorso = "src/test/resources/t2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("T2");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        assertThrows(Error.class, () -> parser.jObject());
    }
    
    @Test
    public void jsonLexerTestValid() {
        String input;
        String percorso = "src/test/resources/tests/step1/valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Valid");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        parser.jObject();
    }
 
    @Test
    public void jsonLexerTestInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step1/invalid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }

        System.out.println("Test Invalid");
        assertThrows(IllegalArgumentException.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
        
     }
}

