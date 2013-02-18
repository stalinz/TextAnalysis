package org.yudin.vacabulary;

import java.util.List;

import org.yudin.hashtable.HashTable;

public class StandartVacabulary extends AbstractVacabulary {

	private static final String CONSTRUCTOR_ASSERT_ERROR = "expectedVacabularySize must be bigest than zero";
	private static final int HASH_TABLE_DEFAULT_SIZE = 10000;
	HashTable table;

	public StandartVacabulary() {
		table = new HashTable(HASH_TABLE_DEFAULT_SIZE);
	}

	public StandartVacabulary(int expectedVacabularySize) {
		assert expectedVacabularySize > 0 : CONSTRUCTOR_ASSERT_ERROR;
		table = new HashTable(expectedVacabularySize);
	}

	@Override
	public boolean findWord(String word) {
		String upperCase = word.toUpperCase();
		return table.findElement(upperCase);
	}

	@Override
	public boolean addWord(String word) {
		if (!findWord(word)) {
			String upperCase = word.toUpperCase();
			table.addElement(upperCase);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<?> allWords() {
		return table.toList();
	}

	@Override
	public String toString() {
		return table.toString();
	}

}
