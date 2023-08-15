class BinNode implements NumberAndSum {
	int data;
	BinNode left;
	BinNode right;
	
	public BinNode(int data, BinNode left, BinNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public int number() {
		
		if (this == null)
			return 0;
		else if (left != null && right != null)
			return 1 + left.number() + right.number();
		else if (left != null && right == null)
			return 1 + left.number();
		else if (left == null && right != null)
			return 1 + right.number();
		
		return 1;
	}
	
	public int sum() {
		
		if (this == null)
			return 0;
		else if (left != null && right != null)
			return data + left.sum() + right.sum();
		else if (left != null && right == null)
			return data + left.sum();
		else if (left == null && right != null)
			return data + right.sum();
		
		return data;
	}
	
}
