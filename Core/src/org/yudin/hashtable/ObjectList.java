package org.yudin.hashtable;

import java.util.ArrayList;
import java.util.List;

public final class ObjectList {
	public static int pCount = 0;
	private ListElement first = null;
	private ListElement last = null;
	private int size;

	public int getSize() {
		return size;
	}

	public ObjectList(Object firstElement) {

		first = last = new ListElement(firstElement);

	}

	public Object getValue(int i) {
		int normal = size - 1;
		if (size <= normal)
			return last.getVal();
		else {
			ListElement curr = first;
			while (normal-- > 0) {
				curr = curr.getNext();
			}
			return curr.getVal();
		}
	}

	public void addValue(Object val) {
		last.setNext(new ListElement(val));
		last = last.getNext();
		size++;
	}

	public int findElement(Object element) {
		ListElement curr = first;
		int count = 0;
		while (listElementExist(curr)) {
			count++;
			if (curr.getVal().equals(element))
				return count;
			curr = curr.getNext();
		}
		return -1;
	}

	@Override
	public String toString() {
		return getChain(first);
	}

	public List<?> toJavaList() {
		ArrayList<Object> result = new ArrayList<Object>();
		ListElement curr = first;
		while (listElementExist(curr)) {
			result.add(curr.getVal());
			curr = curr.getNext();
		}
		return result;
	}

	private boolean listElementExist(ListElement curr) {
		return curr != null;
	}

	private String getChain(ListElement curr) {
		if (listElementExist(curr))
			return curr.val + " -> " + getChain(curr.getNext());
		else
			return "NULL";
	}

}
