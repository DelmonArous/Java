class DHeap<E extends Comparable<E>> {
	private static final int DEFAULT_CAPACITY = 10;
	private E[] array;
	private int currentSize, d;
	
	public DHeap(int d) {
		this(d, DEFAULT_CAPACITY);
	}
	
	public DHeap(int d, int capacity) {
		if (d < 2) {
			try {
				throw new Exception("D too low!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.d = d;
		currentSize = 0;
		array = (E[]) new Comparable[capacity + 1];
	}
	
	public boolean isEmpty() {
		return (currentSize == 0);
	}
	
	public void makeEmpty() {
		currentSize = 0;
	}
	
	public void insert(E e) {
		
		if (currentSize == array.length - 1)
			enlargeArray(array.length*2 + 1);
		
		// Percolate up
		int hole = ++currentSize; // hole is generated at end of array
		for(array[0] = e; hole > 1 && e.compareTo(array[parentIndex(hole)]) < 0; hole = parentIndex(hole))
			array[hole] = array[parentIndex(hole)];
		array[hole] = e; // insert new element
		
	}

	public E deleteMin() {
		
		if (isEmpty())
			try {
				throw new Exception("The heap is empty!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		array[0] = array[1]; // save the root
		array[1] = array[currentSize--]; // replace the root with last element
		percolateDown(1); // percolate down new root

		return array[0];	
	}

	private void percolateDown(int hole) {
		int child;
		int[] children;
		E tmp = array[hole]; // get the value  at index to be percolated down 

		// while target's index is inside the heap's size
		while (hole < currentSize){
			child = hole; // stores the minimum element of index's children or index if index has no children
			children = childrenIndex(hole); // get the index of children
			
			// If the first child exists, assume it is the minimal child
			if (children[0] < currentSize){
				child = children[0];
			}
			
			// For ever other child, if they exist and are less than the current minimal child, they are the minimal child
			for (int i = 1; i < children.length; i++) {
				if (children[i] < currentSize && array[children[i]].compareTo(array[child]) < 0)
					child = children[i];
			}
			
			// If index has children and tmp is bigger than the minimal child, move the minimal child up and
			// set hole to be the index of the minimal child; else index is tmp's proper position, break out of the loop
			if (child != hole && tmp.compareTo(array[child]) > 0) {
				array[hole] = array[child];
				hole = child;
			} else
				break;
        }
		array[hole] = tmp;
		
		/*
		for(; hole*2 <= currentSize; hole = child) {
			child = hole*2;
			if (child != currentSize && array[child+1].compareTo(array[child]) < 0)
				child++;
			if (array[child].compareTo(tmp) < 0)
				array[hole] = array [child];
			else
				break;
		}*/
	}
	
	private void enlargeArray(int newSize) {
		E[] old = array;
		array = (E[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++ )
			array[i] = old[i];
	}

	private int parentIndex(int pos) {
		return (int) Math.ceil( ((double) pos - 1.0) / d);
	}
	
	private int[] childrenIndex(int pos) {
		int[] children = new int[d];
		for (int i = 1; i <= d; i++) 
			children[i-1] = d*(pos-1) + (i+1);
		
		return children;
	}
	
	public int maxHeight() {
		return (int) (Math.ceil( Math.log(currentSize*d - currentSize + 1) / Math.log(d)) - 1);
	}
	
}
