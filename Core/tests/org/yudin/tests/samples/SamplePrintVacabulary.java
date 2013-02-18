package org.yudin.tests.samples;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.yudin.vacabulary.IVacabulary;
import org.yudin.vacabulary.StandartVacabulary;

public class SamplePrintVacabulary {

	@Test
	public void test() {
		IVacabulary vacabulary = new StandartVacabulary();
		try {
			vacabulary.loadFromStream(new FileInputStream(
					"./texts/standartVacabulary"));
			System.out.println(vacabulary);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
