package org.yudin.analyzer;

import java.util.StringTokenizer;

import org.yudin.vacabulary.IVacabulary;

public class Analyzer implements IAnalyzer {

	private IVacabulary vacabulary;
	private int minimalWordLength = 0;

	public Analyzer(IVacabulary vacabulary) {
		setVacabulary(vacabulary);
	}

	@Override
	public void setVacabulary(IVacabulary vacabulary) {
		this.vacabulary = vacabulary;

	}

	@Override
	public AnalysisResult analysisText(String text) {
		int allWordsCount = 0;
		int matchWordsCount = 0;
		StringTokenizer spaceTokenizer = new StringTokenizer(text);
		while (spaceTokenizer.hasMoreElements()) {
			StringTokenizer tokenizer = new StringTokenizer(
					spaceTokenizer.nextToken(), "\".,");
			while (tokenizer.hasMoreElements()) {
				String word = tokenizer.nextToken();
				if (itsWord(word)) {
					if (vacabulary.findWord(word)) {
						matchWordsCount++;
					}
					allWordsCount++;
				}
			}
		}
		return new AnalysisResult(matchWordsCount, allWordsCount);
	}

	private boolean itsWord(String word) {
		return word.length() >= minimalWordLength;
	}

	public int getMinimalWordLength() {
		return minimalWordLength;
	}

	public void setMinimalWordLength(int minimalWordLength) {
		this.minimalWordLength = minimalWordLength;
	}

}
