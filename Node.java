/**
 * Node class for List
 * 
 * @author Joowon Kim
 * @param <E>
 *            : Generic Type
 */
public class Node<E> {
	private Node<E> next = null; // next node
	private E value = null; // element

	/**
	 * Overloaded Constructor
	 */
	public Node() {
		this(null, null);
	}

	/**
	 * Overloaded Constructor
	 */
	public Node(Node<E> next) {
		this(next, null);
	}

	/**
	 * Overloaded Constructor
	 */
	public Node(E value) {
		this(null, value);
	}

	/**
	 * Overloaded Constructor
	 */
	public Node(Node<E> next, E value) {
		this.next = next;
		this.value = value;
	}

	/**
	 * returning next
	 * 
	 * @return next node
	 */
	public Node<E> getNext() {
		return next;
	}

	/**
	 * returning this node's element
	 * 
	 * @return element
	 */
	public E getElement() {
		return value;
	}

	/**
	 * setting next node
	 * 
	 * @param next
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * setting element
	 * 
	 * @param value
	 */

	public void setValue(E value) {
		this.value = value;
	}

	/**
	 * Overrides toString method
	 * 
	 * @see java.lang.Object.toString()
	 * @return converts the element to string and returns
	 */
	public String toString() {
		return value.toString();
	}
}
