/**
 * Class LookUpBST creates a generic binary search tree, 
 * where each node has a key and a value.
 * @author Daniel
 * @param <K> is generic used for the key of the node
 * @param <V> is generic used for the value of the node
 */
class LookUpBST<K extends Comparable<K>, V> {

	//-------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	//-------------------------------------------------------------

	//bad practice to have public inst. variables, but we want to test this...
	//Root of tree
	/**
	 * The root of the tree.
	 */
	public Node<K, V> root;

	// size of the tree (the number of nodes)
	/**
	 * The size of the tree.
	 */
	public int size;
	/**
	 * Returns the size of the tree.
	 * @return size is the size of tree
	 */
	public int size() { return size; }

	//provided binary tree node class
	//bad practice to have public inst. variables,
	//in a public nested class, but we want to test this...
	/**
	 * Node class for the binary search tree.
	 * Each node has a key and a value.
	 * @author Daniel
	 * @param <K> is the generic key
	 * @param <V> is the generic value
	 */
	public static class Node<K, V> {
		/**
		 * The key of the node.
		 */
		K key;                         // sorted by key
		/**
		 * The value of the node.
		 */
		V val;                       // associated data
		/**
		 * The left and right child of the node.
		 */
		Node<K, V> left, right;    // left and right subtrees

		/**
		 * Node constructor assigns key and value to node.
		 * @param key is the key of the node
		 * @param val is the value of the node
		 */
		public Node(K key, V val) { 
			this.key = key; 
			this.val = val;
		}
		/**
		 * Node constructor that assigns key, value, and children to the node.
		 * @param key is the given key
		 * @param val is the given value
		 * @param l is the left child
		 * @param r is the right child
		 */
		public Node(K key, V val, Node<K, V> l, Node<K, V> r) {
			this.key = key;
			this.val = val;
			this.left = l; 
			this.right = r;
		}
	}

	//-------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION 
	//-------------------------------------------------------------


	//-------------------------------------------------------------
	// You can NOT add any instance/static variables in this class.
	// You can add methods if needed but they must be PRIVATE.
	//-------------------------------------------------------------

	//	private Node<K,V> search (Node<K,V> n , K key) {
	//		if(n == null)
	//			return Node<K,V>;
	//		
	//		System.out.println(n.key);
	//		search(n.left);
	//		search(n.right);
	//	}

	/**
	 * Checks if a node is a leaf or not.
	 * @param n is root of tree
	 * @return true if it is and false if it isn't
	 */
	private boolean isLeaf(Node<K,V> n) {
		if(n.left == null && n.right == null)
			return true;
		return false;
	}

	/**
	 * Finds the number of leaves recursively for the tree.
	 * @param curr is the root of the tree
	 * @return the number of leaves at the end of the recursion
	 */
	private int findNumLeaves(Node<K,V> curr) {

		if(curr==null)
			return 0;

		if(isLeaf(curr))
			return 1;

		return findNumLeaves(curr.left) + findNumLeaves(curr.right);
	}

	/**
	 * Finds the height of the tree recursively.
	 * @param curr is the root of the tree
	 * @return the height of the tree at the end of the recursion
	 */
	private int findHeight(Node<K,V> curr) {
		if(curr==null) 
			return -1;

		if(isLeaf(curr))
			return 0;

		if(findHeight(curr.right) > findHeight(curr.left))
			return findHeight(curr.right) + 1;

		return findHeight(curr.left) + 1;
	}

	/**
	 * Checks if a node exists with the given key in the tree.
	 * @param curr is the root of the tree
	 * @param key is the given key
	 * @return true if found and false if not
	 */
	private boolean findNode(Node<K,V> curr, K key) {
		if(curr==null)
			return false;

		if(key.equals(curr.key) || key==curr.key)
			return true;

		if(findNode(curr.left, key))
			return true;

		return findNode(curr.right, key);
	}

	/**
	 * Calls the method findNode and checks if a node with given key is in the tree or not.
	 * @param key is the given key
	 * @return true if found and false if not
	 */
	public boolean contains(K key) {
		// Return true if key is in tree;
		// return false if key is not in tree or if key is null.
		// O(H): H as the tree height 

		return findNode(root,key);
	}

	/**
	 * Gets the value associated with a given key in the tree.
	 * @param curr is root of tree
	 * @param key is given key
	 * @return the value in the node with the given key
	 */
	private V getValue(Node<K,V> curr, K key) {

		if(key.equals(curr.key) || key==curr.key)
			return curr.val;

		if(key.compareTo(curr.key)<0)
			return getValue(curr.left, key);

		return getValue(curr.right, key);

	}

	/**
	 * Returns the value associated with the given key.
	 * @param key is given key
	 * @return is the value of the node with the key
	 */
	public V get(K key) {
		// Return the value associated with the given key if the key is in tree
		// Return null if the key is not in tree
		// throw IllegalArgumentException if key is null
		// O(H): H as the tree height 
		if(key==null)
			throw new IllegalArgumentException("Key cannot be null!");


		return getValue(root, key);
	}

	/**
	 * Puts a new node with given key and value in the tree,
	 * or replaces an existing node with same key.
	 * @param curr is the root of tree
	 * @param key is given key
	 * @param val is given value
	 * @return true if successful and false if not
	 */
	private boolean putNode(Node<K,V> curr, K key, V val) {
		if(key.equals(curr.key)) {
			curr.val = val;
			return true;

		}else if(curr.key.compareTo(key)>0) {
			if(curr.left==null) {
				curr.left = new Node<>(key, val);
				size++;
				return true;

			} else {
				return putNode(curr.left, key, val);
			}
		}else if(curr.key.compareTo(key)<0) {
			if(curr.right==null) {
				curr.right = new Node<>(key, val);
				size++;
				return true;

			}else {
				return putNode(curr.right, key, val);
			}
		}
		return false;
	}

	/**
	 * Calls putNode and puts a new node into the tree with given key and val,
	 * or replaces an existing node.
	 * @param key is given key
	 * @param val is given value
	 * @return result is true if it was successful or false if not
	 */
	public boolean put(K key, V val) {
		// Insert key, val into tree. 
		// If key is in tree, replace already existing value for this key with the given parameter val
		// Return false if key, val cannot be added 
		//    (null keys).
		// Return true for a successful insertion.
		// O(H): H as the tree height  
		if(key==null)
			return false;
		
		if(this.root==null) {
			size=0;
			this.root = new Node<>(key,val);
			size++;
			return true;
		}

		boolean result = putNode(this.root, key, val);
		return result;
	}

	/**
	 * Finds the biggest key of the tree by searching through the most right nodes.
	 * @param t is the root of tree
	 * @param <K> is generic key
	 * @param <V> is generic value
	 * @return the biggest key in tree
	 */
	public static <K, V> K findBiggestKey(Node<K, V> t) {
		// Return the biggest key in the tree rooted at t.
		// Return null if tree is null.
		// O(H): H as the tree height  
		if(t==null)
			return null;

		if(t.right==null) {
			return t.key;
		}

		return findBiggestKey(t.right);
	}

	/**
	 * Calls the method findHeight and finds the height of the tree.
	 * @return the height of the tree
	 */
	public int height() {
		// Return the height of the tree.
		// Return -1 for null trees.
		// O(N): N is the tree size

		return findHeight(root);
	}

	/**
	 * Calls helper method findNumLeaves and returns the number of leaves in the tree.
	 * @return the number of leaves in the tree
	 */
	public int numLeaves() {
		// Return the number of leaf nodes in the tree.
		// Return zero for null trees.
		// O(N): N is the tree size
		//int n = 0;
		//int num = findNumLeaves(root);

		return findNumLeaves(root);
	}

	/**
	 * Returns a string representation of the tree.
	 * @return the string representation of the root
	 */
	public String toString() {
		// Returns a string representation of the tree
		// Change the function call below to
		//        toStringInOrder: to see (IN-ORDER) string representation of LookUpBST while in debug mode
		//        toStringLevelOrder: to see (LEVEL-ORDER) string representation of LookUpBST in level-order traversal while in debug mode

		return toStringInOrder(this.root);
		//return toStringLevelOrder(this.root);
	}

	/**
	 * Returns a string representation of the data in the tree,
	 * according to the IN-ORDER traversal.
	 * @param t is the root of the tree
	 * @return the string crated
	 */
	private String toStringInOrder(Node t){
		// Follow IN-ORDER traversal to include data of all nodes.
		// Example 1: a single-node tree with the root data as "a:112":
		//            toString() should return a:112
		//
		// Example 2: a tree with four nodes:   
		//			 d:310
		//           /   \
		//        a:112   p:367
		//                /  
		//              f:330  
		// toStringInOrder() should return a:112 d:310 f: 330 p:367 

		if (t==null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(toStringInOrder(t.left));
		sb.append(t.key);
		sb.append(":");
		sb.append(t.val);
		sb.append(" ");
		sb.append(toStringInOrder(t.right));
		return sb.toString();
	}

	/**
	 * Creates a string representation of the tree in LevelOrder fashion.
	 * @param t is the root of the tree
	 * @return the string created
	 */
	private String toStringLevelOrder(Node<K, V> t) {
		// Follow LEVEL-ORDER traversal to include data of all nodes.
		// Example: a tree with four nodes:   
		//			 d:310
		//           /   \
		//        a:112   p:367
		//                /  
		//              f:330  
		// toStringLevel() should return
		//                              d:310
		//                              a:112 p:367
		//                              null null f:330 null

		StringBuilder sb = new StringBuilder();
		int capacity = (int)Math.pow(2,size()+1)-1;
		Node<?,?>[] list = new Node<?,?>[capacity];
		list[0] = root;

		int count = 0;
		int level = 0;
		for(int i = 0; i < capacity; i++) {
			if(i == Math.pow(2,level+1)-1) {
				if(count == size) break;
				level++;
				sb.append("\n");
			}
			if(list[i] == null) {
				sb.append("null ");
			}
			else {
				count++;
				sb.append(list[i].key);
				sb.append(":");
				sb.append(list[i].val);
				sb.append(" ");
				if((i*2)+1 < list.length) {
					list[(i*2)+1] = list[i].left;
					list[(i*2)+2] = list[i].right;
				}
			}
		}

		return sb.toString();
	}

	//-------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	//-------------------------------------------------------------

	/**
	 * Main method to test the code of LookUpBST.
	 * @param args command line args
	 */
	public static void main(String[] args) { 
		LookUpBST<String, Integer> t = new LookUpBST<String, Integer>();

		//build the tree / set the size manually
		//only for testing purpose
		Node<String, Integer> node = new Node<>("a", 112);
		Node<String, Integer> node2 = new Node<>("f", 330);
		node2 = new Node<>("p", 367, node2,null);
		node = new Node<>("d", 310,node,node2);
		t.root = node;
		t.size = 4;

		// Current tree:
		//			  d:310
		//           /   \
		//        a:112   p:367
		//                /  
		//              f:330 

		System.out.println(t.toString());
		System.out.println("Height is:" + t.height());
		System.out.println("Nr. Leaf is:" + t.numLeaves());
		//checking basic features
		if (t.root.key == "d" && t.contains("f") && !t.contains("z") && t.height() == 2){
			System.out.println("Yay 1");
		}

		//checking more features
		if (t.numLeaves() == 2 && LookUpBST.findBiggestKey(t.root)=="p"){
			System.out.println("Yay 2");
		}

		t = new LookUpBST<String, Integer>();
		if (t.put("d", 310) && !t.put(null, null) && t.size()==1 && t.height()==0 
				&& t.put("a", 112) && t.put("p", 367) && t.put("f", 330) 
				&& t.get("p") == 367){
			System.out.println("Yay 3"); 	
		}
		if (t.size()==4 && t.height()==2 && t.root.key == "d" && 
				t.root.left.key == "a" && t.root.right.key== "p"
				&& t.root.right.left.key == "f"){
			System.out.println("Yay 4"); 			
		}

		// more insertion
		t.put("s", 465);
		t.put("e", 321);
		t.put("b", 211);

		//			 d:310
		//           /   \
		//        a:112   p:367
		//          \      /   \
		//         b:211 f:330 s:465
		//               /
		//             e:321

		if (t.size()==7 && t.height() == 3 
				&& LookUpBST.findBiggestKey(t.root.left) == "b"){
			System.out.println("Yay 5");					
		}
		// Uncomment the following lines to see an IN-ORDER and a LEVEL-ORDER string representation of the tree, in that order.
		//System.out.println(t.toStringInOrder(t.root));
		//System.out.println(t.toStringLevelOrder(t.root));	
	}
}


