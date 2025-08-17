package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Step5FinalTest {

    private void testFile(String filePath, boolean shouldPass) {
        String input;
        try {
            input = JsonParser.leggiJsonDaFile(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Errore nella lettura del file JSON: " + e.getMessage());
        }

        if (shouldPass) {
            assertDoesNotThrow(() -> {
                JsonLexer lexer = new JsonLexer(input);
                JsonParser parser = new JsonParser(lexer);
                parser.jPayload();
            });
        } else {
            assertThrows(Error.class, () -> {
                JsonLexer lexer = new JsonLexer(input);
                JsonParser parser = new JsonParser(lexer);
                parser.jPayload();
            });
        }
    }

    @Test
    public void testFail1() {
        testFile("src/test/resources/tests/step5/test/fail1.json", false);
    }

    @Test
    public void testFail10() {
        testFile("src/test/resources/tests/step5/test/fail10.json", false);
    }

    @Test
    public void testFail11() {
        testFile("src/test/resources/tests/step5/test/fail11.json", false);
    }

    @Test
    public void testFail12() {
        testFile("src/test/resources/tests/step5/test/fail12.json", false);
    }

    @Test
    public void testFail13() {
        testFile("src/test/resources/tests/step5/test/fail13.json", false);
    }

    @Test
    public void testFail14() {
        testFile("src/test/resources/tests/step5/test/fail14.json", false);
    }

    @Test
    public void testFail15() {
        testFile("src/test/resources/tests/step5/test/fail15.json", false);
    }

    @Test
    public void testFail16() {
        testFile("src/test/resources/tests/step5/test/fail16.json", false);
    }

    @Test
    public void testFail17() {
        testFile("src/test/resources/tests/step5/test/fail17.json", false);
    }

    @Test
    public void testFail18() {
        testFile("src/test/resources/tests/step5/test/fail18.json", false);
    }

    @Test
    public void testFail19() {
        testFile("src/test/resources/tests/step5/test/fail19.json", false);
    }

    @Test
    public void testFail2() {
        testFile("src/test/resources/tests/step5/test/fail2.json", false);
    }

    @Test
    public void testFail20() {
        testFile("src/test/resources/tests/step5/test/fail20.json", false);
    }

    @Test
    public void testFail21() {
        testFile("src/test/resources/tests/step5/test/fail21.json", false);
    }

    @Test
    public void testFail22() {
        testFile("src/test/resources/tests/step5/test/fail22.json", false);
    }

    @Test
    public void testFail23() {
        testFile("src/test/resources/tests/step5/test/fail23.json", false);
    }

    @Test
    public void testFail24() {
        testFile("src/test/resources/tests/step5/test/fail24.json", false);
    }

    @Test
    public void testFail25() {
        testFile("src/test/resources/tests/step5/test/fail25.json", false);
    }

    @Test
    public void testFail26() {
        testFile("src/test/resources/tests/step5/test/fail26.json", false);
    }

    @Test
    public void testFail27() {
        testFile("src/test/resources/tests/step5/test/fail27.json", false);
    }

    @Test
    public void testFail28() {
        testFile("src/test/resources/tests/step5/test/fail28.json", false);
    }

    @Test
    public void testFail29() {
        testFile("src/test/resources/tests/step5/test/fail29.json", false);
    }

    @Test
    public void testFail3() {
        testFile("src/test/resources/tests/step5/test/fail3.json", false);
    }

    @Test
    public void testFail30() {
        testFile("src/test/resources/tests/step5/test/fail30.json", false);
    }

    @Test
    public void testFail31() {
        testFile("src/test/resources/tests/step5/test/fail31.json", false);
    }

    @Test
    public void testFail32() {
        testFile("src/test/resources/tests/step5/test/fail32.json", false);
    }

    @Test
    public void testFail33() {
        testFile("src/test/resources/tests/step5/test/fail33.json", false);
    }

    @Test
    public void testFail4() {
        testFile("src/test/resources/tests/step5/test/fail4.json", false);
    }

    @Test
    public void testFail5() {
        testFile("src/test/resources/tests/step5/test/fail5.json", false);
    }

    @Test
    public void testFail6() {
        testFile("src/test/resources/tests/step5/test/fail6.json", false);
    }

    @Test
    public void testFail7() {
        testFile("src/test/resources/tests/step5/test/fail7.json", false);
    }

    @Test
    public void testFail8() {
        testFile("src/test/resources/tests/step5/test/fail8.json", false);
    }

    @Test
    public void testFail9() {
        testFile("src/test/resources/tests/step5/test/fail9.json", false);
    }

    @Test
    public void testPass1() {
        testFile("src/test/resources/tests/step5/test/pass1.json", true);
    }

    @Test
    public void testPass2() {
        testFile("src/test/resources/tests/step5/test/pass2.json", true);
    }

    @Test
    public void testPass3() {
        testFile("src/test/resources/tests/step5/test/pass3.json", true);
    }
}
