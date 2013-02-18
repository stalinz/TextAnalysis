package org.yudin.vacabulary;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractVacabulary implements IVacabulary {
	private static final String SAVED_ERROR = "Vacabulary has been not saved to stream. Reason: {}";

	private static final String ENDL = "\n";

	Logger log = LoggerFactory.getLogger(AbstractVacabulary.class);

	@Override
	public int loadFromStream(InputStream stream) {
		int size = 0;
		Scanner scanner = new Scanner(stream);

		while (scanner.hasNext()) {
			addWord(scanner.next());
			size++;
		}
		return size;
	}

	@Override
	public abstract List<?> allWords();

	@Override
	public void addVacabulary(IVacabulary anotherVacabulary) {
		List<?> anotherWords = anotherVacabulary.allWords();
		for (Object o : anotherWords) {
			addWord((String) o);
		}
	}

	@Override
	public boolean saveToStream(OutputStream out) {
		List<?> words = allWords();
		StringBuffer buffer = new StringBuffer();
		for (Object word : words) {
			buffer.append((String) word).append(ENDL);
		}

		try {
			out.write(buffer.toString().getBytes());
		} catch (IOException e) {
			log.error(SAVED_ERROR, e.getMessage());
			return false;
		}

		return true;
	}
}
