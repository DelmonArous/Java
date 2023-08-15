import java.util.Iterator;

class DobbeltLenketListe<T extends UtNorsk & OutEnglish> implements Stabel<T> {
	private Node forste, siste;
	private int antall;
	
	private class Node {
		Node neste;
		T obj;

		Node(T t) {
			obj = t;
		}
	}
	
	DobbeltLenketListe() {
		forste = null;
		siste = null;
		antall = 0;
	}
	
	public boolean isEmpty() {
		return (forste == null);
	}
	
	public void push(T t) {
		Node n = new Node(t);
		if (!isEmpty()) {
			n.neste = forste;
			forste = n;
		}
		else {
			forste = n;
		}
		antall++;
	}
	
	public T top() {
		return forste.obj;
	}
	
	public T pop() {
		if(!isEmpty()) {
			T obj = forste.obj;
			forste = forste.neste;
			antall--;
			return obj;
		}
		return null;
	}
	
	public Iterator<T> iterator() {
		return new LenkeListeIterator();
	}
	
	class LenkeListeIterator implements Iterator<T> {
		int teller = 0;
		Node n = forste;
		
		public boolean hasNext() {
			return (teller < antall);
		}
		
		public T next() {
			teller++;
			Node etter = n;
			n = etter.neste;
			return etter.obj;
		}
		
		public void remove() {}
		
	}
}
