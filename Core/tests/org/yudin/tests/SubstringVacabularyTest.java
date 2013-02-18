package org.yudin.tests;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;
import org.yudin.vacabulary.SubstringVacabulary;

public class SubstringVacabularyTest extends SubstringVacabulary {

	private static final String ENDL = "\n";

	@Test
	public void testAddFromCodeAndFindWord() {
		SubstringVacabulary vacabulary = new SubstringVacabulary();
		vacabulary.addWord("foo");
		assertTrue(vacabulary.findWord("foobar"));
		assertTrue(vacabulary.findWord("barfoo"));
		assertTrue(vacabulary.findWord("foo bar"));
		assertFalse(vacabulary.findWord("fobar"));
		assertFalse(vacabulary.findWord("bar"));
	}

	@Test
	public void testAddFromStreamAndFindWord() {
		StringBuilder test = new StringBuilder("world");
		test.append(ENDL);
		test.append("hello");
		InputStream in = new ByteArrayInputStream(test.toString().getBytes());
		SubstringVacabulary vacabulary = new SubstringVacabulary();
		vacabulary.loadFromStream(in);
		assertTrue(vacabulary.findWord("world"));
		assertTrue(vacabulary.findWord("hello"));
		assertTrue(vacabulary.findWord("hello world"));
		assertTrue(vacabulary.findWord("worldhello"));
		assertFalse(vacabulary.findWord("hell"));
		assertFalse(vacabulary.findWord("word"));

	}

	@Test
	public void testSaveToStreamFunction() {
		SubstringVacabulary vacabulary = new SubstringVacabulary();
		String testWord = "foo";
		vacabulary.addWord(testWord);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		vacabulary.saveToStream(out);
		String result = new String(out.toByteArray());
		result.contains(testWord);
	}
}
