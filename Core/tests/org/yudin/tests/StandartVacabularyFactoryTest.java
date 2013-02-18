package org.yudin.tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.yudin.vacabulary.StandartVacabulary;
import org.yudin.vacabulary.StandartVacabularyFactory;
import org.yudin.vacabulary.SubstringVacabulary;

public class StandartVacabularyFactoryTest {

	StandartVacabulary vacabulary;

	@Before
	public void setUp() {
		SubstringVacabulary substringVacabulary = new SubstringVacabulary();
		String text = "hello world is a most popular programm of the wor";
		substringVacabulary.addWord("hell");
		substringVacabulary.addWord("wor");
		vacabulary = StandartVacabularyFactory.generate(substringVacabulary,
				new ByteArrayInputStream(text.getBytes()), 5);
	}

	@Test
	public void testLengthVacabulary() {
		assertTrue(vacabulary.allWords().size() == 2);
	}

	@Test
	public void testSearchWord() {
		assertTrue(vacabulary.findWord("hello"));
		assertTrue(vacabulary.findWord("world"));
		assertFalse(vacabulary.findWord("wor"));

	}

}
