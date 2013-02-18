package org.yudin.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.yudin.analyzer.AnalysisResult;
import org.yudin.analyzer.Analyzer;
import org.yudin.analyzer.IAnalyzer;
import org.yudin.vacabulary.IVacabulary;
import org.yudin.vacabulary.StandartVacabulary;

public class AnalyzerTest {
	private static final String TEST_TEXT = "hello world is a most popular programm of the world, but \"foo bar\" - standart example pair";
	IAnalyzer analyzer;

	@Before
	public void setUp() {
		IVacabulary vacabulary = new StandartVacabulary();
		vacabulary.addWord("hello");
		vacabulary.addWord("world");
		vacabulary.addWord("foo");
		vacabulary.addWord("bar");
		analyzer = new Analyzer(vacabulary);
	}

	@Test
	public void test_nonSemanticMathText() {
		String text = "Mather was washed frame, father briwed meth";
		AnalysisResult result = analyzer.analysisText(text);
		assertTrue(result.getWordsAllCount() == 7);
		assertTrue(result.getWordsMathCount() == 0);
		assertFalse(result.check(0.01, 1));
	}

	@Test
	public void testFullWord() {
		String text = TEST_TEXT;
		AnalysisResult result = analyzer.analysisText(text);
		assertTrue(result.getWordsAllCount() == 17);
		assertTrue(result.getWordsMathCount() == 5);
		assertFalse(result.check(0.5, 1));
		assertTrue(result.check(5.0 / 17.0, 0.6));
	}

	@Test
	public void testMinimalWordLengthUseful() {
		String text = TEST_TEXT;
		if (analyzer instanceof Analyzer) {
			Analyzer analyz = (Analyzer) analyzer;
			analyz.setMinimalWordLength(3);
			AnalysisResult result = analyz.analysisText(text);
			assertTrue(result.getWordsAllCount() == 13);
			assertTrue(result.getWordsMathCount() == 5);
			assertFalse(result.check(0.5, 1));
			assertTrue(result.check(5.0 / 15.0, 0.6));
		} else {
			fail("you used not Analyzer class");
		}
	}

	void printInt(int val) {
		System.out.println(val);
	}
}
