package org.yudin.tests.samples;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yudin.analyzer.AnalysisResult;
import org.yudin.analyzer.Analyzer;
import org.yudin.analyzer.StringFactory;
import org.yudin.vacabulary.IVacabulary;
import org.yudin.vacabulary.StandartVacabulary;

public class SampleCheckAllTestFiles {

	private static final String STANDART_VACABULARY_PATH = "./texts/standartVacabulary";
	Logger log = LoggerFactory.getLogger(SampleCheckAllTestFiles.class);
	String political_files[] = { "./texts/little1", "./texts/little2",
			"./texts/text1", "./texts/little7", "./texts/text3",
			"./texts/text4", "./texts/wiki" };
	String nonPoliticalFiles[] = { "./texts/little3_NP", "./texts/little4_np",
			"./texts/little5_np", "./texts/little6_NP", "./texts/text2_NP",
			"./texts/text5_NP", "./texts/PeaceAndWar",

	};
	File file = new File("./texts/politics");

	IVacabulary vacabulary = new StandartVacabulary();
	Analyzer analyzer;

	@Before
	public void setUp() {
		try {
			vacabulary.loadFromStream(new FileInputStream(
					STANDART_VACABULARY_PATH));
			analyzer = new Analyzer(vacabulary);
			analyzer.setMinimalWordLength(4);
		} catch (FileNotFoundException e) {
			fail("File " + STANDART_VACABULARY_PATH + " not founded");
		}
	}

	@Test
	public void testAllFiles() {
		File files[] = getAllFiles();
		for (File file : files) {
			try {
				String text = StringFactory.readFile(file.getAbsolutePath());
				AnalysisResult result = analyzer.analysisText(text);
				System.out.println(result.getRatio());
//				log.info("{}",result.getRatio());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	File[] getAllFiles() {
		return file.listFiles();
	}

}
