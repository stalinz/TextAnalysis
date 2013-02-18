package org.yudin.tests.samples;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.junit.Test;
import org.yudin.vacabulary.StandartVacabulary;
import org.yudin.vacabulary.StandartVacabularyFactory;
import org.yudin.vacabulary.SubstringVacabulary;

public class SampleCreateFileVacabulary {
	SubstringVacabulary vacabulary;
	String political_files[] = { "./texts/little1", "./texts/little2",
			"./texts/text1", "./texts/little7", "./texts/text3",
			"./texts/text4", "./texts/wiki" };

	@Test
	public void test() {
		vacabulary = new SubstringVacabulary();
		try {
			vacabulary.loadFromStream(new FileInputStream(
					"./texts/Base_vacabulary"));

			StandartVacabulary result = new StandartVacabulary();
			for (String file : political_files)
				result.addVacabulary(getVacabularyFromText(file));
			assertTrue(result.allWords().size() > 0);

			result.saveToStream(new FileOutputStream("./resultTesTFile"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private StandartVacabulary getVacabularyFromText(String file) {
		StandartVacabulary result = null;
		try {
			result = StandartVacabularyFactory.generate(vacabulary,
					new FileInputStream(file), 4);
		} catch (FileNotFoundException e) {
			fail("File not exist ! " + file);
		}
		return result;

	}
}
