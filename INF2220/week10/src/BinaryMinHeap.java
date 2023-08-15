class BinaryMinHeap<T extends Comparable<T>> implements HeapInterface<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private Node[] array;
	private int currentSize;
	
	private class Node {
		T obj;
		int pri;
				
		Node(T obj, int pri) {
			this.obj = obj;
			this.pri = pri;
		}
	}
	
	public BinaryMinHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public BinaryMinHeap(int capacity) {
		currentSize = 0;
		array = (Node[]) new Object[capacity + 1];
	}
	
	public boolean isEmpty() {
		return (currentSize == 0);
	}
	
	public void makeEmpty() {
		currentSize = 0;
	}
	
	public void insert(int pri, T o) {
		Node n = new Node(o, pri);
		
		if (currentSize == array.length - 1)
			enlargeArray(array.length*2 + 1);
		
		// Percolate up
		int hole = ++currentSize; // hole is generated at end of array
		for(array[0].pri = pri; pri < array[hole/2].pri; hole /= 2)
			array[hole] = array[hole/2];
		array[hole] = n; // insert new element
		
	}

	public T deleteMin() {
		
		if (isEmpty()) {
			try {
				throw new Exception("The heap is empty!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		array[0] = array[1]; // save the root
		array[1] = array[currentSize--]; // replace the root with last element
		percolateDown(1); // percolate down new root

		return array[0].obj;	
	}

	private void percolateDown(int hole) {
		int child;
		Node tmp = array[hole];
		
		for(; hole*2 <= currentSize; hole = child) {
			child = hole*2;
			if (child != currentSize && array[child+1].pri < array[child].pri)
				child++;
			if (array[child].pri < tmp.pri)
				array[hole] = array [child];
			else
				break;
		}
		array[hole] = tmp;
	}
	
	private void enlargeArray(int newSize) {
		Node[] old = array;
		array = (Node[]) new Object[newSize];
		for (int i = 0; i < old.length; i++ )
			array[i] = old[i];
	}

	

}