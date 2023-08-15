class BinSearchTree<E extends Comparable<E>> {
	/** The root in the BST */
	private BinNode root;
	
	private class BinNode implements Comparable<E> {
		E key; // value of the node: a single word
		BinNode right, left; // root of right and left subtree, respectively
		int height, depth; // height and depth of the node, respectively
		
		BinNode(E key, int depth) {
			this.key = key;
			right = null;
			left = null;
			height = 0;
			this.depth = depth;
		}
		
		public int compareTo(E e) {
			return key.compareTo(e);
		}
	}
	
	/** The constructor yields initializing the root */
	public BinSearchTree() {
		root = null;
	}
	
	/** Resets the BST */
	public void makeEmpty() {
		root = null;
	}
	
	/** Checks if the BST is empty */
	public boolean isEmpty() {
		return (root == null);
	}

	/** Returns the total height of the BST */
	public int height() {
		return height(root);
	}
	
	/** Returns the height of a single node */
	public int height(BinNode t) {
		return (t == null ? -1 : t.height);
	}	
	
	/** Returns the number of nodes in the BST */
	public int number() {
		return number(root);
	}
	
	/** Returns the number of nodes in a given subtree, where t is the root */
	public int number(BinNode t) {
		
		if (t == null)
			return 0;
		
		return 1 + number(t.left) + number(t.right);
	}
	
	/** Find the first word in the dictionary */
	public E findMin() {
		return findMin(root).key;
	}
	
	public BinNode findMin(BinNode t) {
		
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		
		return findMin(t.left);	
	}

	/** Find the final word in the dictionary */
	public E findMax() {
		return findMax(root).key;
	}
	
	public BinNode findMax(BinNode t) {
		
		if (t == null)
			return null;
		else if (t.right == null)
			return t;
		
		return findMax(t.right);	
	}
	
	/** Checks if there is a node with key x is in the BST */
	public boolean contains(E x) {
		return contains(x, root);
	}
	
	public boolean contains(E x, BinNode t) {
		
		if (t == null)
			return false;
		
		int compareResult = x.compareTo(t.key);
		
		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true;
	
	}
	
	/** Generate a new node with key x and insert it in the BST */
	public void insert(E x) {
		root = insert(x, root, 0);
	}
	
	public BinNode insert(E x, BinNode t, int d) {
		
		if (t == null)
			return new BinNode(x,d);
			
		int compareResult = x.compareTo(t.key);
		
		if (compareResult < 0)
			t.left = insert(x, t.left, d+1);
		else if (compareResult > 0)
			t.right = insert(x, t.right, d+1);
		else
			; // Duplicate; do nothing
		
		t.height = max(height(t.left), height(t.right)) + 1;
		return t;
	}

	private static int max(int n, int m) {
		return (n > m ? n : m);
	}

	
	public void remove(E x) {
		root = remove(x, root);
	}
	
	/** Remove the node with key x if present in the BST */
	public BinNode remove(E x, BinNode t) {
		
		if (t == null)
			return t;
		
		int compareResult = x.compareTo(t.key);
		
		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) { // this node got two children
			t.key = findMin(t.right).key;
			t.right = remove(t.key, t.right);
		}
		else 
			t = (t.left != null) ? t.left : t.right;
		
		return t;
	}
	
	/** Returns an array of length equal to the height of the BST,
	 *  where each element indicates how many nodes have a given depth */
	public int[] depthArray() {
		
		if (isEmpty()) { 
			return null; // The BST is empty
		}
		
		int[] depths = new int[height()+1];
		depths = depthArray(root, depths);
		return depths;
	}
	
	/** Recursive method to add depths of all nodes in a given subtree */
	public int[] depthArray(BinNode t, int[] depths) {
		
		if (t == null)
			return depths;
		
		depths = depthArray(t.left, depths);
		depths = depthArray(t.right, depths);
		depths[t.depth]++;
		return depths;
	}
	
	/** Returns the depth of the BST */
	public int treeDepth() {
		return depthArray().length;
	}
	
	/** Returns the average depth of all the nodes in the BST */
	public double aveDepth() {
		double sum = 0.;
		
		for (int i = 0; i < depthArray().length; i++) {
			sum += i*depthArray()[i];
		}
			
		return sum/number();
	}
	
}