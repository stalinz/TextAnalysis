package org.yudin.vacabulary;

import java.io.InputStream;
import java.util.Scanner;

public class StandartVacabularyFactory {
	private static final String NonLiteral = "[^а-яА-Яa-zA-Z\\-]{1,}";

	public static StandartVacabulary generate(SubstringVacabulary vacabulary,
			InputStream master, int minimalSize) {
		StandartVacabulary result = new StandartVacabulary();
		Scanner scanner = new Scanner(master);
		while (scanner.hasNext()) {
			String word = scanner.next();
			word = cleanWord(word);
			if (word.length() >= minimalSize && vacabulary.findWord(word))
				result.addWord(word);
		}
		return result;

	}

	private static String cleanWord(String word) {
		return word.replaceAll(NonLiteral, "");
	}
}
