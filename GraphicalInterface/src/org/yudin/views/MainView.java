package org.yudin.views;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yudin.analyzer.AnalysisResult;
import org.yudin.analyzer.Analyzer;
import org.yudin.wigets.LimitWiget;
import org.yudin.wigets.MessageBoxFactory;
import org.yudin.wigets.TextWiget;
import org.yudin.wigets.VacabularyWiget;

public class MainView extends Composite {

	private static final String ANALYZ_THAT_FTEXT = "Анализировать текст";
	private static final String NO = "Категория не совпадает";
	private static final String YES = "Категория совпадает";
	private static final String MATH_PERCTENTS = "% совпадений со словарём.\n";
	private static final String SUCCESS = "Текст успешно проанализирован\n";
	private static final String HIGH_LIMIT = "Верхний предел";
	private static final String LOW_LIMIT = "Нижний предел";

	private VacabularyWiget vacabularyWiget;
	private TextWiget textWiget;
	private LimitWiget lowestLimit;
	private LimitWiget higthLimit;

	private static Logger log = LoggerFactory.getLogger(MainView.class);

	public MainView(Composite arg0, int arg1) {
		super(arg0, SWT.BORDER);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		vacabularyWiget = initVacabularyWiget();
		textWiget = initTextWiget();
		lowestLimit = initLimitWiget(LOW_LIMIT);
		higthLimit = initLimitWiget(HIGH_LIMIT);
		vacabularyWiget.loadFromStream(vacabularyWiget.loadProcedure(new File(
				"defaultDictonary")));
		initAnlyzeButton();
	}

	private VacabularyWiget initVacabularyWiget() {
		VacabularyWiget vaWiget = new VacabularyWiget(this, SWT.NONE);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		vaWiget.setLayoutData(data);

		return vaWiget;
	}

	private TextWiget initTextWiget() {
		TextWiget tWiget = new TextWiget(this, SWT.NONE);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		tWiget.setLayoutData(data);
		return tWiget;
	}

	private LimitWiget initLimitWiget(String label) {
		LimitWiget limitWiget = new LimitWiget(this, SWT.NONE, label);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.CENTER;
		limitWiget.setLayoutData(data);
		return limitWiget;
	}

	private Button initAnlyzeButton() {
		Button button = new Button(this, SWT.PUSH);
		button.setText(ANALYZ_THAT_FTEXT);
		button.addMouseListener(analyzeItButtonPushed);
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = GridData.CENTER;
		data.verticalAlignment = GridData.CENTER;
		button.setLayoutData(data);
		return button;
	}

	MouseListener analyzeItButtonPushed = new MouseListener() {

		@Override
		public void mouseUp(MouseEvent arg0) {
			Analyzer analyzer = new Analyzer(vacabularyWiget);
			AnalysisResult result = analyzer.analysisText(textWiget.toString());
			double low = getLowBarier();
			double higth = getHigthBarier();
			boolean isSemanticCompare = result.check(low, higth);
			double ratio = result.getRatio();
			log.debug("Word count: {} Compare count: {}",
					result.getWordsAllCount(), result.getWordsMathCount());
			log.debug("Result: {} is correct text: {}", ratio,
					isSemanticCompare);
			MessageBoxFactory.showBox("Текст проанализирован",
					genResultString(ratio, isSemanticCompare), getShell());

		}

		@Override
		public void mouseDown(MouseEvent arg0) {
		}

		@Override
		public void mouseDoubleClick(MouseEvent arg0) {

		}
	};

	private String genResultString(double percent, boolean isSemanticCompare) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(SUCCESS);
		buffer.append(percent * 100.0);
		buffer.append(MATH_PERCTENTS);
		buffer.append(isSemanticCompare ? YES : NO);
		return buffer.toString();
	}

	private double getHigthBarier() {
		double result = 1;
		double val = higthLimit.getValue() / 100.0;
		if (val < 1 && val > 0)
			result = val;
		return result;
	}

	private double getLowBarier() {
		double result = 0;
		double val = lowestLimit.getValue() / 100.0;
		if (val < 1 && val > 0)
			result = val;
		return result;
	}
}
