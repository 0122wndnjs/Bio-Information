/**
 * List interface
 * 
 * @author Joowon Kim
 */
public interface List<E> {

	/**
	 * Clear all data from the list
	 */
	public void clear();

	/**
	 * Insert the element at the position
	 * 
	 * @param item
	 *            : Inserted element
	 */
	public void insert(int index, E item);

	/**
	 * Adding element at the end of the list
	 * 
	 * @param item
	 *            : Added element
	 */
	public void add(E item);

	/**
	 * Remove the element at the position
	 */
	public void remove(int index);

	/**
	 * Getting the element one left position
	 * 
	 * @return index
	 */
	public E prev(int index);

	/**
	 * Getting the element one right position
	 * 
	 * @return index
	 */
	public E next(int index);

	/**
	 * @return the number of elements
	 */
	public int length();

	/**
	 * Changing the Nodes to a string from head to end
	 * 
	 * @return String
	 */
	public String toString();

	/**
	 * Make the list contents to be reversed
	 */
	public void reverse();

	/**
	 * @return index : The element in position
	 */
	public E getValue(int index);

}
