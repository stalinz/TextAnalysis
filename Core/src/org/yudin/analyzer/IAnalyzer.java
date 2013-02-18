package org.yudin.analyzer;

import org.yudin.vacabulary.IVacabulary;

public interface IAnalyzer {
	public void setVacabulary(IVacabulary vacabulary);

	public AnalysisResult analysisText(String text);
}
