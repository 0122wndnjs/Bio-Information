/**
 * SLList class - implementation of List interface
 * 
 * @author Joowon Kim
 * @param <E>
 *            : Generic Type
 */

public class SLList<E> implements List<E> {
	Node<E> head = new Node<E>(); // head

	int length = 0; // list length

	/**
	 * Clear all data from the list
	 */
	public void clear() {
		head.setNext(null);
		head.setValue(null);
		length = 0;
	}

	/**
	 * Insert an element at the position
	 * 
	 * @param index
	 *            : location
	 * @param item
	 *            : inserted element
	 */
	public void insert(int index, E item) {
		if ((index < 0) || (index > length)) {
			System.out.println("Wrong Index");
		} else if ((index == 0) && (length == 0)) {
			head.setValue(item);
			length++;
		} else if (index == 0) {
			Node<E> nhead = new Node<E>(head, item);
			head = nhead;
			length++;
		} else if (index == length) {
			Node<E> node = head;
			Node<E> nnode = new Node<E>(item);
			for (int i = 0; i < length - 1; i++) {
				node = node.getNext();
			}
			node.setNext(nnode);
			length++;
		} else {
			Node<E> prev = head;
			Node<E> preserve;
			Node<E> nnode = new Node<E>(item);
			for (int i = 0; i < index - 1; i++) {
				prev = prev.getNext();
			}
			preserve = prev.getNext();
			prev.setNext(nnode);
			nnode.setNext(preserve);
			length++;
		}
	}

	/**
	 * Add an element at the end of the list
	 * 
	 * @param item
	 *            : added element
	 * @see SLList.insert(int index, E item)
	 */
	public void add(E item) {
		insert(length, item);
	}

	/**
	 * Remove the element
	 * 
	 * @param index
	 *            : location
	 */
	public void remove(int index) {
		if ((index < 0) || (index >= length)) {
			System.out.println("Wrong Index");
		} else if ((index == 0) && (length == 1)) {
			head.setValue(null);
			length--;
		} else if (index == 0) {
			Node<E> nhead = head.getNext();
			head = nhead;
			length--;
		} else if (index == length - 1) {
			Node<E> node = head;
			for (int i = 0; i < length - 2; i++) {
				node = node.getNext();
			}
			node.setNext(null);
			length--;
		} else {
			Node<E> prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.getNext();
			}
			prev.setNext(prev.getNext().getNext());
			length--;
		}
	}

	/**
	 * Getting the element one left position
	 * 
	 * @param index
	 *            : location
	 * @return null if head, otherwise element to the left of index
	 */

	public E prev(int index) {
		if ((index > length) || (index < 1)) {
			System.out.println("Wrong Index");
			return null;
		} else {
			return getValue(index - 1);
		}
	}

	/**
	 * Getting the element one right position
	 * 
	 * @param index
	 *            : location
	 * @return null if head, otherwise element to the right of index
	 */
	public E next(int index) {
		if ((index >= length - 1) || (index < 0)) {
			System.out.println("Wrong Index");
			return null;
		} else {
			return getValue(index + 1);
		}
	}

	/**
	 * @return The number of elements in the list
	 */

	public int length() {
		return length;
	}

	/**
	 * Converts to the string from head to end
	 * 
	 * @return sized
	 */
	public String toString() {
		String sized = "";
		for (int i = 0; i < length; i++) {
			if (i == 0)
				sized = getValue(i).toString();
			else
				sized = sized + " ==> " + getValue(i).toString();
		}
		return sized;
	}

	/**
	 * Reverse the list
	 * 
	 */
	public void reverse() {
		if (length > 1) {
			Node<E> nhead = new Node<E>(getValue(length - 1));
			Node<E> node = nhead;
			for (int i = 0; i < length - 1; i++) {
				Node<E> nnode = new Node<E>(getValue(length - i - 2));
				node.setNext(nnode);
				node = node.getNext();
			}
			head = nhead;
		}
	}

	/**
	 * Get the element
	 * 
	 * @param index
	 * @return element at the position
	 */
	public E getValue(int index) {
		Node<E> node = head;
		if ((index >= length) || (index < 0)) {
			System.out.println("Wrong Index");
			return null;
		} else {
			for (int i = 0; i < index; i++)
				node = node.getNext();
			return node.getElement();
		}
	}

	/**
	 * Get head
	 * 
	 * @return head
	 */
	public Node<E> getHead() {
		return head;
	}

	/**
	 * Get last
	 * 
	 * @return last
	 */
	public Node<E> getLast() {
		Node<E> node = head;
		for (int i = 0; i < length - 1; i++)
			node = node.getNext();
		return node;
	}

	/**
	 * insert list
	 * 
	 * @param list
	 * @param index
	 */
	public void insertList(SLList<E> list, int index) {
		if ((index < 0) || (index > length)) {
			System.out.println("Wrong Index");
		} else if (list.length == 0) {
			System.out.print("");
		} else if (length == 0) {
			head = list.head;
			length = list.length;
		} else if (index == length - 1) {
			getLast().setNext(list.head);
			length += list.length;
		} else {
			Node<E> prev = head;
			Node<E> preserve;
			for (int i = 0; i < index; i++)
				prev = prev.getNext();
			preserve = prev.getNext();
			prev.setNext(list.head);
			list.getLast().setNext(preserve);
			length += list.length;
		}
	}
}
