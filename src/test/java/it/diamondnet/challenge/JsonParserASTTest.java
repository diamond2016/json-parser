package it.diamondnet.challenge;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class JsonParserASTTest {

    @Test
    public void jsonParserASTTest1() {
        String input;
        String percorso = "src/test/resources/test1.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test1.json - AST (v2)");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        JsonObjectNode node = parser.jObject();
        System.out.println(node.toString());
        assertEquals(3, node.getChildren().size());
    }

    @Test
    public void jsonParserASTTest2() {
        String input;
        String percorso = "src/test/resources/test2.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test2.json - AST (v2)");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        JsonArrayNode node = parser.jArray();
        System.out.println(node.toString());
        assertEquals(10, node.getChildren().size());
    }
    
    @Test
    public void jsonParserASTTestArray() {
        String input="[\"title\",\"descriptionf\",\"url\",\"frecency\"]";
       
        System.out.println("Test test array - AST (v2)");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        JsonArrayNode node = parser.jArray();
        System.out.println(node.toString());
        assertEquals(4, node.getChildren().size());
    }

    @Test
    public void jsonParserASTTest3() {
        String input;
        String percorso = "src/test/resources/test3.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test3.json - AST (v2)");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        JsonObjectNode node = parser.jObject();
        System.out.println(node.toString());
        assertNotNull(node);
    }

      @Test
    public void jsonParserASTTest4() {
        String input;
        String percorso = "src/test/resources/test4.json";
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Test test4.json - AST (v2)");
        JsonLexer lexer = new JsonLexer(input);
        JsonParser parser = new JsonParser(lexer);
        JsonObjectNode node = parser.jObject();
        System.out.println(node.toString());
        assertNotNull(node);
    }

    @Test
    public void jsonParserASTString1() {
        String input="\"1.43.0\"";    
        System.out.println("Test string1 - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jString();
        });
    }

    @Test
    public void jsonParserASTString2() {
        String input="\"^pom\\\\.xml$\"";    
        System.out.println("Test string2 - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jString();
        });
    }

    @Test
    public void jsonParserASTObject1() {
        String input="{\"java.jdt.ls.vmargs\": \"default: -XX:+UseParallelGC -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Dsun.zip.disableMemoryMapping=true -Xmx2G -Xms100m -Xlog:disable\"}";
        System.out.println("Test object1 - step5");
        assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

    @Test
    public void jsonParserASTObject2() {
        String input = "{\n\"letter\": \"\u0041\"\n}";
        System.out.println("Test object2 - step5");
                assertDoesNotThrow(() -> {
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject();
        });
    }

}