package org.yudin.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;
import org.yudin.vacabulary.StandartVacabulary;

public class StandartVacabularyTest {
	private static final String ENDL = "\n";
	private static String[] mustBeenWord = { "hello", "world" };
	private static String[] mustNotBeen = { "hell", "word" };

	@Test
	public void testAddByCodeAndFindWord() {
		StandartVacabulary vacabulary = new StandartVacabulary();
		for (String s : mustBeenWord) {
			vacabulary.addWord(s);
		}
		defaultStandartVacabularyTest(vacabulary);
	}

	@Test
	public void testAddFromStreamAndFindWord() {
		StringBuilder test = new StringBuilder();
		for (String s : mustBeenWord) {
			test.append(s);
			test.append(ENDL);
		}
		InputStream in = new ByteArrayInputStream(test.toString().getBytes());
		StandartVacabulary vacabulary = new StandartVacabulary();
		vacabulary.loadFromStream(in);
		defaultStandartVacabularyTest(vacabulary);

	}

	private void defaultStandartVacabularyTest(StandartVacabulary vacabulary) {
		for (int i = 0; i < mustBeenWord.length; i++) {
			assertTrue(vacabulary.findWord(mustBeenWord[i]));
			assertFalse(vacabulary.findWord(mustNotBeen[i]));
		}
	}

	@Test
	public void testSaveToStreamFunction() {
		StandartVacabulary vacabulary = new StandartVacabulary();
		String testWord = "foo";
		vacabulary.addWord(testWord);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		vacabulary.saveToStream(out);
		String result = new String(out.toByteArray());
		result.contains(testWord);
	}

	@Test
	public void testCountOfDobles() {
		StandartVacabulary vacabulary = new StandartVacabulary();
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		vacabulary.addWord("foo");
		System.out.println(vacabulary.allWords().size());
		assertTrue(vacabulary.allWords().size() == 1);
	}

}
