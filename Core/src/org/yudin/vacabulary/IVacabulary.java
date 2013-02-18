package org.yudin.vacabulary;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface IVacabulary {
	public boolean findWord(String word);

	public boolean addWord(String word);

	public int loadFromStream(InputStream in);

	public boolean saveToStream(OutputStream out);

	public void addVacabulary(IVacabulary anotherVacabulary);
	
	public abstract List<?> allWords();

}
