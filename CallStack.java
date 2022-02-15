import java.util.Iterator;

/**
 * Class CallStack implements Iterable interface and creates a generic stack.
 * It contains methods to operate with the nodes and their values in the stack.
 * @author Daniel
 * @param <T> generic type of the stack
 */
class CallStack<T> implements Iterable<T> {
	// You'll want some instance variables here
	/**
	 * Private generic node to track the top of the stack.
	 */
	private Node<T> top;
	/**
	 * Private variable to track size of stack.
	 */
	private int size = 0;

	/**
	 * No argument constructor of CallStack class .
	 * Creates an instance of the class when called.
	 */
	public CallStack() {
		//setup what you need
		top = null;
	}

	/**
	 * Pushes a generic item in the stack.
	 * Assigns top based on number of items.
	 * @param item is the item to psuh in stack
	 */
	public void push(T item) {
		//push an item onto the stack
		//you may assume the item is not null
		//O(1)
		if(isEmpty()) {
			top = new Node<>(item);
		}else {
			Node<T> currNode = top;
			top = new Node<>(item);
			top.setNext(currNode);
			currNode.setPrev(top);
		}
		size++;
	}

	/**
	 * Pops a generic item from the stack.
	 * Removes item and adjusts position of top.
	 * @return temp the popped item
	 */
	public T pop() {
		//pop an item off the stack
		//if there are no items on the stack, return null
		//O(1)
		T temp;

		if(isEmpty()) {
			temp = null;
		}else {
			temp = top.getValue();
			if(top.getNext() == null) {
				top = null;
			}else {
				top = top.getNext();
				top.setPrev(null);

			}
			size--;
		}

		//replace this dummy return statement
		return temp;
	}

	/**
	 * Returns the top value of the stack without removing it.
	 * @return null if stack is empty or the top value if not
	 */
	public T peek() {
		//return the top of the stack (but don't remove it)
		//if there are no items on the stack, return null
		//O(1)
		if(isEmpty()) {
			return null;
		}else {
			return top.getValue();
		}
		//replace this dummy return statement
	}

	/**
	 * Creates a string version of the values in the stack.
	 * @return s is the string created
	 */
	public String toString() {
		//Create a string of the stack where each item
		//is separated by a space. The top of the stack
		//should be shown to the right and the bottom of
		//the stack on the left.

		//Hint: Reuse the provided code from another class
		//instead of writing this yourself!

		//O(n)
		String s;

		if(isEmpty()) {
			s = "";
		}else {
			s = Node.listToStringBackward(top);
		}
		//replace this dummy return statement
		return s;
	}

	/**
	 * Removes everything from the stack.
	 */
	public void clear() {
		//remove everything from the stack
		//O(1)
		top = null;
		size = 0;
	}

	/**
	 * Returns the size of the stack.
	 * @return size is the size of the stack
	 */
	public int size() {
		//return the number of items on the stack
		//O(1)

		//replace this dummy return statement
		return size;
	}

	/**
	 * Checks if the stack is empty.
	 * @return true if it is and false if it isn't
	 */
	public boolean isEmpty() {
		//return whether or not the stack is empty
		//O(1)
		if(top == null) {
			return true;
		}else {
			return false;
		}
		//replace this dummy return statement
	}

	/**
	 * Creates an array representation of the stack.
	 * Puts every value of the stack in an array with top being at the first index.
	 * @return arr being the array created
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		//Return an array representation of the stack.
		//The top of the stack should be element 0
		//in the array.

		//O(n)
		T arr[] = (T[]) new Object[size];
		int i = 0;
		Node<T> current = top;

		while(current != null) {
			arr[i] = current.getValue();
			current = current.getNext();
			i++;
		}
		//replace this dummy return statement
		return arr;
	}

	/**
	 * Returns an iterator created for the stack.
	 * @return new MyIterator() is the iterator
	 */
	public Iterator<T> iterator() {
		//Return an iterator that traverses from
		//the top of the stack to the bottom of
		//the stack.

		//The iterator's hasNext() and next() methods
		//must both be O(1)

		//next() should throw a NullPointerException
		//if you try to use next when there are no
		//more items

		//replace this dummy return statement
		return new MyIterator();
	}

	/**
	 * MyIterator class that implements Iterator and creates an iterator for the stack.
	 * @author Daniel
	 */
	private class MyIterator implements Iterator<T> {
		/**
		 * Private generic node to represent current node.
		 */
		private Node<T> current = top;
		/**
		 * Private generic temporary value.
		 */
		private T temp;

		/**
		 * Returns if the current node is null or not.
		 * @return current != null
		 */
		public boolean hasNext( ) {
			return current != null; 
		}

		/**
		 * Returns the value of the current node and moves to the next one.
		 * @return temp is the temporary value of the current node.
		 */
		public T next( ) {
			if(current == null) {
				throw new NullPointerException("No more items available!");
			}
			temp = current.getValue();
			current = current.getNext();
			return temp;
		}
	}

	/**
	 * Main method to test the previous code.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		CallStack<String> s1 = new CallStack<>();
		s1.push("a");
		s1.push("b");
		//s1.push("c");
		//s1.push("d");

		CallStack<Integer> s2 = new CallStack<>();
		s2.push(1);
		s2.push(2);
		s2.push(3);

		//System.out.println(s1.toString());
		//System.out.println(s2.toString());
		//System.out.println(s2.size());

		if(s1.toString().equals("a b") && s1.toArray()[0].equals("b") && s1.toArray()[1].equals("a") && s1.toArray().length == 2) {
			System.out.println("Yay 1");
		}

		if(s1.peek().equals("b") && s2.peek().equals(3) && s1.size() == 2 && s2.size() == 3) {
			System.out.println("Yay 2");
		}

		if(s1.pop().equals("b") && s2.pop().equals(3) && s1.size() == 1 && s2.size() == 2) {
			System.out.println("Yay 3");
		}

		if(s1.toString().equals("a") && s1.peek().equals("a") && s2.peek().equals(2) && s1.pop().equals("a") && s2.pop().equals(2) && s1.size() == 0 && s2.size() == 1) {
			System.out.println("Yay 4");
		}

		if(s1.toString().equals("") && s1.peek() == null && s2.peek().equals(1) && s1.pop() == null && s2.pop().equals(1) && s1.size() == 0 && s2.size() == 0) {
			System.out.println("Yay 5");
		}

		s2.push(10);
		s2.push(20);
		s2.push(30);
		if(s1.isEmpty() && s1.toArray().length == 0 && !s2.isEmpty()) {
			s2.clear();
			if(s2.isEmpty()) {
				System.out.println("Yay 6");
			}
		}

		CallStack<Integer> s3 = new CallStack<>();
		s3.push(3);
		s3.push(2);
		s3.push(1);

		//System.out.println(s3.toString());

		int i = 1;
		for(Integer item : s3) {
			if(i == item) System.out.println("Yay " + (6+i));
			else
				System.out.println(item);
			i++;
		}
	}
}