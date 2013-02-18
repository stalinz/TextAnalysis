package org.yudin.analyzer;

public class AnalysisResult {
	private int wordsMathCount;
	private int wordsAllCount;

	public AnalysisResult(int wordsMathCount, int wordsAllCount) {
		setWordsAllCount(wordsAllCount);
		setWordsMathCount(wordsMathCount);
	}

	public boolean check(double minimalPercent, double maximalPercent) {
		double ratio = getRatio();
		return ratio >= minimalPercent && ratio <= maximalPercent;
	}

	public double getRatio() {
		double ratio = (double) wordsMathCount / (double) wordsAllCount;
		return ratio;
	}

	public int getWordsMathCount() {
		return wordsMathCount;
	}

	void setWordsMathCount(int wordsMathCount) {
		this.wordsMathCount = wordsMathCount;
	}

	public int getWordsAllCount() {
		return wordsAllCount;
	}

	void setWordsAllCount(int wordsAllCount) {
		this.wordsAllCount = wordsAllCount;
	}

}
