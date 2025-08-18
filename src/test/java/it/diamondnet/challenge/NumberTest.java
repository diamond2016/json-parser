package it.diamondnet.challenge;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class NumberTest {
// Esempi di utilizzo

@Test
void testIntegerValueTest() {
    JsonLexer lexer = new JsonLexer("123");
    assertTrue(lexer.isValidJsonNumber("123"));
}

@Test
void testNegativeValueTest() {
    JsonLexer lexer = new JsonLexer("-0.5");
    assertTrue(lexer.isValidJsonNumber("-0.5"));
}

@Test
void testfractionalValueTest() {
    JsonLexer lexer = new JsonLexer("1.23e-5");
    assertTrue(lexer.isValidJsonNumber("1.23e-5"));
}

@Test
void testFailIntegerValueTest() {
    JsonLexer lexer = new JsonLexer("01");
    assertFalse(lexer.isValidJsonNumber("01"));
}

@Test
void testFailfractionalValueTest() {
    JsonLexer lexer = new JsonLexer("1.");
    assertFalse(lexer.isValidJsonNumber("1."));
}

@Test
void testFailInvalidValueTest() {
    JsonLexer lexer = new JsonLexer("123a");
    assertFalse(lexer.isValidJsonNumber("123a"));
}

}
