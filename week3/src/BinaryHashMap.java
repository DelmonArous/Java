class BinaryHashMap {
	private static final int size = 2;
	private int numberofkeys, numberofvalues;
	private List[] thelist = new List[size];
	
	private class List {
		String key;
		Object value;
		List next;
		
		List(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}

	public void put(String key, Object value) {
		int hash = key.hashCode() % size;
		List l = thelist[hash]; 
		
		if (l != null) {
			// inserting a duplicate key-value pair, 
			// hence overwrite the current pair with the old pair
			if (l.key.equals(key))
				l.value = value; 
			else {
				while (l.next != null)
					l = l.next;
				
				l.next = new List(key, value);
				numberofkeys++;
			}
		} else {
			thelist[hash] = new List(key, value);
			numberofkeys++;
		}
	
	}
	
	public boolean remove(String key) {
		int hash = key.hashCode() % size;
		List current = thelist[hash];	
		List previous = thelist[hash];
		
		if (current != null) {
			while (!current.key.equals(key)) {
				if (current.next == null)
					return false;
				else {
					previous = current;
					current = current.next;
				}
			}	
		} else
			return false;
		
		if (current == thelist[hash]) 
			thelist[hash] = thelist[hash].next;
		else 
			previous.next = current.next;
		
		numberofkeys--;
		return true;
	}
	
	public String[] keys() {
		String[] allkeys = new String[numberofkeys];
		List l;
		int k = 0;
		
		for (int i = 0; i < thelist.length; i++) {
			if (thelist[i] != null) {
				l = thelist[i];
				while (l != null) {
					allkeys[k++] = l.key;
					l = l.next;
				}
			}
		}
		
		return allkeys;
	}
	
	public Object[] toArray() {
		Object[] allvalues = new Object[numberofvalues];
		List l;
		int k = 0;
		
		for (int i = 0; i < thelist.length; i++) {
			if (thelist[i] != null) {
				l = thelist[i];
				while (l != null) {
					allvalues[k++] = l.key;
					l = l.next;
				}
			}
		}
		
		return allvalues;
	}
}
