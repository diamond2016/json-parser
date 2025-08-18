package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;


public class JsonParserStep3Test {

   
    @Test
    public void jsonParserStep3TestInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step3/invalid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Invalid step3");
        assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }
    
    @Test
    public void jsonParserStep3TestValid() {
        String input;
        String percorso = "src/test/resources/tests/step3/valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Valid step3");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        assertDoesNotThrow(() -> {
            parser.jObject();
        });
    }
    
        @Test
    public void jsonParserStep3TestBoolInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step3/invalidbool.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test invalid bool step3");
        assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jBool();
        });
        
    }

        @Test
    public void jsonParserStep3TestNumberInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step3/invalidnumber.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test invalid number step3");
        assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jNumber();
        });
    }

    @Test
    public void jsonParserStep3TestStringValid() {
        String input="\"mamma\"";
        System.out.println("Test valid string step3");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        assertDoesNotThrow(() -> {
            parser.jString();
        });
    }

    @Test
    public void jsonParserStep3TestNumberValid() {
        String input;
        String percorso = "src/test/resources/tests/step3/validnumber.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test valid number step3");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        assertDoesNotThrow(() -> {
            parser.jNumber();
        });

    }
}

