package it.diamondnet.challenge;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import org.junit.jupiter.api.Test;

public class JsonParserStep5Test {

    @Test
    public void jsonParserStep5Test1() {
        String input;
        String percorso = "src/test/resources/test1.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test1.json - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

    @Test
    public void jsonParserStep5Test2() {
        String input;
        String percorso = "src/test/resources/test2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test2.json - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jArray();
        });
    }
    
    @Test
    public void jsonParserStep5TestArray() {
        String input="[\"title\",\"descriptionf\",\"url\",\"frecency\"]";
       
        System.out.println("Test test array - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jArray();
        });
    }

    @Test
    public void jsonParserStep5Test3() {
        String input;
        String percorso = "src/test/resources/test3.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test3.json - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

      @Test
    public void jsonParserStep5Test4() {
        String input;
        String percorso = "src/test/resources/test4.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test4.json - step5");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        parser.jObject();/*
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });*/
    }

    @Test
    public void jsonParserStep5String1() {
        String input="\"1.43.0\"";    
        System.out.println("Test string1 - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jString();
        });
    }

    @Test
    public void jsonParserStep5String2() {
        String input="^pom\\\\.xml$";    
        System.out.println("Test string2 - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jString();
        });
    }
}