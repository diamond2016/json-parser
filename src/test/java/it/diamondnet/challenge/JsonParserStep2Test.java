package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertThrows;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class JsonParserStep2Test {

   
    @Test
    public void jsonParserStep2TestInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step2/invalid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Invalid step2");
        assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }
    
    @Test
    public void jsonParserStep2TestValid() {
        String input;
        String percorso = "src/test/resources/tests/step2/valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Valid step2");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        parser.jObject();
        assert true; // Se non lancia eccezioni, il test è passato
    }
    
    @Test
    public void jsonParserStep2TestInvalid2() {
        String input;
        String percorso = "src/test/resources/tests/step2/invalid2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Invalid2 step2");

       assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }
    
    @Test
    public void jsonParserStep2TestValid2() {
        String input;
        String percorso = "src/test/resources/tests/step2/valid2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Valid2 step2");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        parser.jObject();
        assert true; // Se non lancia eccezioni, il test è passato
    }    
}

