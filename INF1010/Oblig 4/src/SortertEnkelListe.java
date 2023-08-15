import java.util.Iterator;

class SortertEnkelListe<T extends Comparable<T> & Lik> implements AbstraktSortertEnkelListe<T> {
	private Node forste;
	private int antall;

	private class Node {
		T obj;
		Node neste;
		
		Node(T t) {
			obj = t;
		}
	}
	
	public boolean erTom() {
		return (forste == null);
	}
	
	public SortertEnkelListe() {
		forste = null;
		antall = 0;
	}
	
	public void leggInnSortert(T t) {
		Node n = new Node(t);
		if (!erTom()) {
			if (n.obj.compareTo(forste.obj) < 0) {
				n.neste = forste;					  
				forste = n;
				antall++;
			}
			else {
				Node etter = forste.neste;
				Node før = forste;
				while (etter != null) {
					if (n.obj.compareTo(etter.obj) < 0) {
						break;
					}
					før = etter;
					etter = etter.neste;
				}
				n.neste = før.neste;
				før.neste = n;
				antall++;
			}
		}
		else {
			forste = n;
			antall++;
		}
	}
	
	public T finnObjekt(String s) {
		Node n = forste;
		if (!erTom()) {
			for (int i = antall; i > 0; i--) {
				if (n.obj.samme(s)) {
					return n.obj;
				}
				else {
					n = n.neste;
				}
			}
		}
		return null;
	}
	
	public Iterable<T> itererGjennomAlle() {
		return new Ukjent1();
	}
	
	class Ukjent1 implements Iterable<T> { 
		public Iterator<T> iterator() {return new Ukjent2();} 
	}
	
	class Ukjent2 implements Iterator<T> { 
		int teller = 0;
		Node n = forste;
		public boolean hasNext() {
			return (teller < antall);
		}
		public T next() {
			teller++;
			Node før = n;
			n = n.neste;
			return før.obj;
		}
		public void remove() {}
	}
	
}