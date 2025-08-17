package it.diamondnet.challenge;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CharacterTest {

	@Test
	void test() {
		JsonLexer lexer = new JsonLexer("\\");
		assertTrue(lexer.isAlphaValid('\\'));
	}

	@Test
	void test2() {
		JsonLexer lexer = new JsonLexer("(");
		assertTrue(lexer.isAlphaValid('('));
		
		lexer = new JsonLexer(")");
		assertTrue(lexer.isAlphaValid(')'));
		
		lexer = new JsonLexer("-");
		assertTrue(lexer.isAlphaValid('-'));
		
		lexer = new JsonLexer(" ");
		assertTrue(lexer.isAlphaValid(' '));
		
		lexer = new JsonLexer("//");
		assertTrue(lexer.isAlphaValid('/'));
		
	}

}
