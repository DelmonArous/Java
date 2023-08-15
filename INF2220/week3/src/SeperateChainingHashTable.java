class SeperateChainingHashTable<E extends Comparable<E>> {
	private List[] theLists;
	private static final int DEFAULT_TABLE_SIZE = 101;
	private int currentSize;	
	
	private class List implements Comparable<E> {
		E key;
		List next;
		
		List(E key) {
			this.key = key;
		}
		
		public int compareTo(E e) {
			return key.compareTo(e);
		}
		
	}
	
	public SeperateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeperateChainingHashTable(int size) {
		theLists = new List[nextPrime(size)];
	}
	
	private int myhash(E x) {
		int hashVal = x.hashCode() % theLists.length;
		
		if (hashVal < 0) 
			hashVal += theLists.length;
		
		return hashVal;
	}
	
	public boolean contains(E x) {
		List l = theLists[myhash(x)];
		
        while (l != null) {
            if(l.key.compareTo(x) == 0) {
                return true;
            }
            l = l.next;
        }
        
        return false;
	}
	
	public void insert(E x) {
		List l = theLists[myhash(x)]; 
		
		if (l != null) {
			while (l.next != null)
				l = l.next;
				
			l.next = new List(x);
		} else
			theLists[myhash(x)] = new List(x);
	
		if (++currentSize > theLists.length) {
			rehash();
		}		
		
	}
	
	public void remove(E x) {
		List current = theLists[myhash(x)];	
		List previous = theLists[myhash(x)];
		
		if (current != null) {
			while (current.key.compareTo(x) != 0) {
				if (current.next == null)
					return;
				else {
					previous = current;
					current = current.next;
				}
			}	
		} else
			return;
		
		if (current == theLists[myhash(x)]) 
			theLists[myhash(x)] = theLists[myhash(x)].next;
		else 
			previous.next = current.next;
		
		currentSize--;
	}
	
	public void rehash() {
		List[] oldLists = theLists;
		
		// Create new double-sized, empty table
		theLists = new List[nextPrime(2*theLists.length)];
		
		// Copy table over
		currentSize = 0;
		List l;
		for (int i = 0; i < oldLists.length; i++)
			l = oldLists[i];
			while (l != null) {
				insert(l.key);
				l = l.next;
			}
	}
	
    private static int nextPrime(int n) {
        if (n % 2 == 0)
        	n++;

        for(; !isPrime(n); n+=2)
        	;
        
        return n;    
    }
    
	private static boolean isPrime(int n) {
		if(n == 2 || n == 3)
			return true;

		if( n == 1 || n % 2 == 0 )
			return false;

		for( int i = 3; i * i <= n; i += 2 )
			if( n % i == 0 )
				return false;

		return true;
	}
    
}
