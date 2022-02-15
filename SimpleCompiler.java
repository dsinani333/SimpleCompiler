//These are all the imports you are allowed, don't add any more!
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * SimpleCompiler class that will take a postfix program, compile it and print the result.
 * @author Daniel
 */
class SimpleCompiler {
	/**
	 * Opens the specified file and puts all symbols it reads in a queue as strings.
	 * @param filename is the name of the file
	 * @return head is the front of the queue
	 * @throws IOException if file does not exist or doesn't open
	 */
	public static Node<String> fileToQueue(String filename) throws IOException {
		//given a file name, open that file in a scanner and create a queue of nodes
		//the head of the queue of nodes should be the start of the queue
		//the values in the nodes should be the strings read in each time you call
		//next() on the scanner

		File newFile = new File(filename);
		if(!newFile.exists()) {
			throw new IOException("File does not exist!");
		}

		Scanner s = new Scanner(newFile);

		Node<String> front = new Node<>(s.next());
		Node<String> head = front;
		Node<String> currNode;
		//int size = 1;

		while(s.hasNext()) {
			currNode = new Node<>(s.next());
			front.setNext(currNode);
			currNode.setPrev(front);
			front = currNode;
			//size ++;
		}
		s.close();

		return head;
	}

	/**
	 * Takes in a queue of nodes and does arithmetic based on the values and symbols in the queue.
	 * Does as many operations as specified by numSymbols.
	 * @param input is the given front of the queue
	 * @param numSymbols is the number of symbols to operate on
	 * @return input is the remaining queue
	 */
	public Node<String> compile(Node<String> input, int numSymbols) {
		//Given an input queue of symbols, process the number of symbols
		//specified (numSymbols) and update the callStack and symbols
		//variables appropriately to reflect the state of the "SimpleCompiler"
		//(see below the "do not edit" line for these variables).

		//Return the remaining queue items.

		//For example, if input is the head of a linked list 3 -> 2 -> +
		//and numSymbols=2, you would push 3 and push 2, then return the linked
		//list with just the + node remaining.
		String s;
		String var1;
		String var2;
		Integer num1 = 0;
		Integer num2 = 0;
		Integer result = 0;
		int i;
		
		for(i=0; i<numSymbols; i++) {
			s = input.getValue();
			//if(s==null) {
			//	break;
			//}

			if(s.equals("+")) {
				if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
					var1 = (String) callStack.pop();
					if(symbols.contains(var1))
						num1 = symbols.get(var1);
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num1 + num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num1 + num2;
					}
				}else {
					num1 = (Integer) callStack.pop();
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num1 + num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num1 + num2;
					}
				}
				callStack.push(result);
				
			}else if(s.equals("-")) {
				if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
					var1 = (String) callStack.pop();
					if(symbols.contains(var1))
						num1 = symbols.get(var1);
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num2 - num1;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num2 - num1;
					}
				}else {
					num1 = (Integer) callStack.pop();
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num2 - num1;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num2 - num1;
					}
				}
				callStack.push(result);
				
			}else if(s.equals("*")) {
				if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
					var1 = (String) callStack.pop();
					if(symbols.contains(var1))
						num1 = symbols.get(var1);
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num1 * num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num1 * num2;
					}
				}else {
					num1 = (Integer) callStack.pop();
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						result = num1 * num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						result = num1 * num2;
					}
				}
				callStack.push(result);
				
			}else if(s.equals("/")) {
				if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
					var1 = (String) callStack.pop();
					if(symbols.contains(var1))
						num1 = symbols.get(var1);
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						if(num2>num1)
							result = num2/num1;
						else
							result = num1/num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						
						if(num2>num1)
							result = num2/num1;
						else
							result = num1/num2;
					}
				}else {
					num1 = (Integer) callStack.pop();
					
					if(callStack.peek().toString().charAt(0) > 'a' && callStack.peek().toString().charAt(0) < 'z') {
						var2 = (String) callStack.pop();
						if(symbols.contains(var2)) {
							num2 = symbols.get(var2);}
						
						if(num2>num1)
							result = num2/num1;
						else
							result = num1/num2;
						
					}else {
						num2 = (Integer) callStack.pop();
						
						if(num2>num1)
							result = num2/num1;
						else
							result = num1/num2;
					}
				}
				callStack.push(result);
				
			}else if(s.equals("=")) {
				num1 = (Integer) callStack.pop();
				var1 = (String) callStack.pop();
				symbols.put(var1, num1);

			}else if(s.equals("print")) {
				System.out.println(callStack.pop());

			}else if(s.charAt(0)>'a' && s.charAt(0)<'z') {
				callStack.push(s);

			}else {
				callStack.push(Integer.parseInt(s));
			}
			input = input.getNext();

			if(input==null)
				break;
		}

		return input;
	}

	/**
	 * Substitude method for the main method if main will be called without any parameters.
	 * Used for testing code.
	 */
	public static void testThisCode() {
		//edit this as much as you want, if you use main without any arguments,
		//this is the method that will be run instead of the program
		try {
			Node<String> head = fileToQueue("C:\\Users\\Daniel\\Desktop\\project2r1\\s005-dsinani-p2\\prog\\sample1.txt");
			while(head != null) {
				System.out.print(head.getValue()+".");
				head = head.getNext();
			}
			System.out.println(" End");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//System.out.println("You need to put test code in testThisCode() to run SimpleCompiler with no parameters.");
	}

	//--------------------DON'T EDIT BELOW THIS LINE--------------------
	//----------------------EXCEPT TO ADD JAVADOCS----------------------

	//don't edit these...
	/**
	 * Private string array with arithmetic operators.
	 */
	public static final String[] INT_OPS = {"+","-","*","/"};
	/**
	 * private string array with assign arithmetic operators.
	 */
	public static final String[] ASSIGN_OPS = {"=","+=","-=","*=","/="};

	//or these...
	/**
	 * Instance of the class CallStack.
	 */
	public CallStack<Object> callStack = new CallStack<>();
	/**
	 * Instance of the class LookUpBST.
	 */
	public LookUpBST<String, Integer> symbols = new LookUpBST<>();

	/**
	 * Main method to run SimpleCompiler class.
	 * Enters debug mode with the correct argument.
	 * @param args command line args
	 */
	public static void main(String[] args) {
		//this is not a testing main method, so don't edit this
		//edit testThisCode() instead!

		if(args.length == 0) {
			testThisCode();
			return;
		}

		if(args.length != 2 || !(args[1].equals("false") || args[1].equals("true"))) {
			System.out.println("Usage: java SimpleCompiler [filename] [true|false]");
			System.exit(0);
		}

		try {
			(new SimpleCompiler()).runCompiler(args[0], args[1].equals("true"));
		}
		catch(IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	//provided, don't change this
	/**
	 * Runs the compiler based on what command line argument given.
	 * If command was true runs with debug mode and shows step by step,
	 * else just runs the program and shows result only.
	 * @param filename is the name of the file specified
	 * @param debug is the boolean value to enter debug mode
	 * @throws IOException if the file doesn't exist or doesn't open properly
	 */
	public void runCompiler(String filename, boolean debug) throws IOException {
		Node<String> input = fileToQueue(filename);
		System.out.println("\nProgram: " + Node.listToString(input));

		if(!debug) {
			while(input != null) {
				input = compile(input, 10);
			}
		}
		else {
			Scanner s = new Scanner(System.in);
			for(int i = 1; input != null; i++) {
				System.out.println("\n######### Step " + i + " ###############\n");
				System.out.println("----------Step Output----------");
				input = compile(input, 1);
				System.out.println("----------Lookup BST---------");
				System.out.println(symbols);
				System.out.println("----------Call Stack--------");
				System.out.println(callStack);
				if(input != null) {
					System.out.println("----------Program Remaining----");
					System.out.println(Node.listToString(input));
				}
				System.out.println("\nPress Enter to Continue");
				s.nextLine();
			}
		}
	}
}