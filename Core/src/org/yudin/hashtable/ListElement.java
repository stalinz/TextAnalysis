package org.yudin.hashtable;

class ListElement {

	private ListElement next;
	Object val;

	public ListElement(Object value) {
		val = value;
		next = null;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public ListElement getNext() {
		return next;
	}

	public void setNext(ListElement next) {
		this.next = next;
	}

}