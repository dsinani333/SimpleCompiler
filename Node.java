//Do not edit this class, just add JavaDocs
//You may edit the main method for testing things if you want
//but do not change the actual class structure.

/**
 * Generic node class used to build the linked list implementations of data structures.
 * @author Daniel
 * @param <T> generic type of the nodes
 */
class Node<T> {
	/**
	 * Private generic value of the node.
	 */
	private T value;
	/**
	 * Private next node.
	 */
	private Node<T> next;
	/**
	 * Private previous node.
	 */
	private Node<T> prev;

	/**
	 * Argument constructor of Node class.
	 * @param value is the value of the node
	 */
	public Node(T value) {
		this.value = value;
	}

	/**
	 * Returns the node value.
	 * @return value is the node's value.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value of the node.
	 * @param value is the value the node will recieve
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Returns the next node linked to current node.
	 * @return this.next is the next node
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * Links current node to the node next.
	 * @param next is the given node
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Returns the previous node linked to current node.
	 * @return this.prev is the previous node
	 */
	public Node<T> getPrev() {
		return this.prev;
	}

	/**
	 * Links the current node to the node prev.
	 * @param prev is the given node
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	/**
	 * Creates a string representation of the data in the list.
	 * @param head is the first node in the list
	 * @return the string created
	 */
	public static String listToString(Node<?> head) {
		StringBuilder ret = new StringBuilder();
		Node<?> current = head;
		while(current != null) {
			ret.append(current.value);
			ret.append(" ");
			current = current.getNext();
		}
		return ret.toString().trim();
	}
	/**
	 * Creates a string of the data in the list, but backwards.
	 * @param head is the first node in the list
	 * @return the string created
	 */
	public static String listToStringBackward(Node<?> head) {
		Node<?> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}

		StringBuilder ret = new StringBuilder();
		while(current != null) {
			ret.append(current.value);
			ret.append(" ");
			current = current.getPrev();
		}
		return ret.toString().trim();
	}

	/**
	 * Main method to test the previous code.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want

		//make nodes
		Node<String> n1 = new Node<>("A");
		Node<String> n2 = new Node<>("B");
		Node<String> n3 = new Node<>("C");

		//connect forward references
		n1.setNext(n2);
		n2.setNext(n3);

		//connect backward references
		n3.setPrev(n2);
		n2.setPrev(n1);

		//print forward and backward
		System.out.println(Node.listToString(n1));
		System.out.println(Node.listToStringBackward(n1));
	}
}