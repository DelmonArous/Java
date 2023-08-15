class BinSearchTree {
	private BinNode root;
	
	public BinNode insert(int x, BinNode t) {
		
		if (t == null) {
			return new BinNode(x, null, null);	
		}
		
		if (x < t.data)
			t.left = insert(x, t.left);
		else if (x > t.data)
			t.right = insert(x, t.left);
		else {
			if (t.right == null) 
				t.right = new BinNode(x, null, null);
			else
				t.right = insert(x, t.right);
		}
		
		return t;
	}
	
	public int number(BinNode t) {
		if (t == null)
			return 0;
		
		return 1 + number(t.left) + number(t.right);
	}
	
	public int sum(BinNode t) {
		if (t == null)
			return 0;
		
		return t.data + sum(t.left) + sum(t.right);
	}
	
}