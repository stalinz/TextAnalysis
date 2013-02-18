package org.yudin.vacabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SubstringVacabulary extends AbstractVacabulary {

	private Set<String> vacabulary = new TreeSet<String>();

	@Override
	public boolean findWord(String word) {
		String upperWord = word.toUpperCase();
		for (Object s : vacabulary.toArray()) {
			if (upperWord.contains((String) s))
				return true;
		}
		return false;
	}

	@Override
	public boolean addWord(String word) {
		vacabulary.add(word.toUpperCase());
		return true;
	}

	@Override
	public List<?> allWords() {
		List<Object> list = new ArrayList<Object>();
		Object wordsArray[] = vacabulary.toArray();
		for (Object word : wordsArray) {
			list.add(word);
		}
		return list;
	}

}
