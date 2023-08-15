import java.util.Iterator;

class Tabell<T> implements AbstraktTabell<T> {
	private int antall = 0; // antall elementer i tabellen
	private T [] beholder;
	
	public Tabell(int l) {
		beholder = (T[]) new Object[l]; // tabellen får lengden l
	}

	// Metoden setter et objekt inn i tabellen på en oppgitt plass (indeks). Metoden returnerer sann
	// eller usann avhengig om operasjonen gikk bra eller ikke
	public boolean leggInn(T t, int indeks) {
		if (indeks < beholder.length && beholder[indeks] == null) { // hvis  indeks er mindre enn lengden på tabellen
			beholder[indeks] = t;									// OG sjekker om plassen er tilgjengelig i tabellen
			antall++;
			return true;
		}
		else {
			return false;
		}
	}

	// Metoden finner et objekt basert på en indeks
	public T finnObjekt(int indeks) {
		if (indeks < beholder.length) { // sjekker om indeks er mindre enn lengden på tabellen
			return beholder[indeks];
		}
		return null;
	}

	// Metoden returnere en Iterator over tabellen
	public Iterable<T> itererGjennomAlle() {
		return new Ukjent1();
	}

	class Ukjent1 implements Iterable<T> { 
		public Iterator<T> iterator() {return new Ukjent2();} 
	}
	
	class Ukjent2 implements Iterator<T> { 
		int teller = 0; 
		public boolean hasNext() {
			return (teller < antall);
		}
		public T next() {
			teller++;
			return beholder[teller-1];
		}
		public void remove() {}
	}
}