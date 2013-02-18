package org.yudin.hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashTable {

	private static final int NONE_ELEMENT = -1;
	private final int size;
	private ObjectList[] table;

	public HashTable(int size) {
		this.size = size;
		table = new ObjectList[size];
	}

	public void addElement(Object element) {
		int cubicle = calcCode(element);
		if (table[cubicle] == null)
			table[cubicle] = new ObjectList(element);
		else {
			table[cubicle].addValue(element);
		}
	}

	public boolean findElement(Object element) {
		boolean result;
		int cubicle = calcCode(element);
		if (cellExist(cubicle))
			result = table[cubicle].findElement(element) != NONE_ELEMENT;
		else
			result = false;
		return result;
	}

	private int calcCode(Object val) {
		int result = 0;
		result = Math.abs(val.hashCode()) % size;
		return result;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		int i = 1;
		for (ObjectList list : table) {
			if (list != null) {
				result.append("[" + i + "] -> ");
				result.append(list.toString() + "\n");
			}
			// else
			// result.append("NULL\n");
			i++;
		}
		return result.toString();
	}

	public List<?> toList() {
		ArrayList<Object> result = new ArrayList<Object>();
		for (int i = 0; i < size; i++) {
			if (cellExist(i)) {
				result.addAll(table[i].toJavaList());
			}
		}
		return result;
	}

	private boolean cellExist(int i) {
		return table[i] != null;
	}

}
