package it.diamondnet.challenge;

/**
 * Step1 of the coding challenge
 */
public class Step1 {
    public static void main(String[] args) {
       if (args.length != 1) {
            System.out.println("Usage: java Step1 <path_to_json_file>");
            return;
        }
        
        String percorso = args[0];
        String input;
        try { 
            input = JsonParser.leggiJsonDaFile(percorso);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }
        
        System.out.println("Parsing JSON from: " + percorso);

        try { 
            JsonLexer lexer = new JsonLexer(input);
            JsonParser parser = new JsonParser(lexer);
            parser.jObject(); // Inizia il parsing dell'oggetto JSON
            System.out.println("JSON parsed successfully.");    
        } catch (Exception e) {
            System.err.println("Errore durante il parsing del JSON: " + e.getMessage());
            System.exit(1);
        }
        System.exit(0);

    }
}
