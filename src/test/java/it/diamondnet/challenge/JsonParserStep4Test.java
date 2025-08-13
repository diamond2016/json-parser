package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import org.junit.jupiter.api.Test;


public class JsonParserStep4Test {

   
    @Test
    public void jsonParserStep4TestInvalid() {
        String input;
        String percorso = "src/test/resources/tests/step4/invalid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test Invalid step4");
        assertThrows(Error.class, () -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

    @Test
    public void jsonParserStep4TestEmptyArray() {
        String input;
        String percorso = "src/test/resources/tests/step4/arr_empty_valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test array empty step4");
        
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jArray();
        });
     }

    @Test
    public void jsonParserStep4TestArray1Valid() {
        String input;
        String percorso = "src/test/resources/tests/step4/array1_valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test array1 valid step4");
        
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jArray();
        });
     }


    @Test
    public void jsonParserStep4TestValid() {
        String input;
        String percorso = "src/test/resources/tests/step4/valid.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test valid step4");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

        @Test
    public void jsonParserStep4TestValid2() {
        String input;
        String percorso = "src/test/resources/tests/step4/valid2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test valid step4");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }


}
