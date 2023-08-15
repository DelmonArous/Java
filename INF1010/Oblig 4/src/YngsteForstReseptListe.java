/*
 * YngsteForstReseptListe er en subklasse av EnkelReseptListe.
 * Den eneste metoden denne subklassen overkjører er metoden
 * som setter inn en resept. Her kommer reseptene som blir lagt inn
 * først, fremst i listen.
 */

class YngsteForstReseptListe extends EnkelReseptListe {
	
	public void leggInn(Resepter r) {
		Node n = new Node(r);
		n.neste = forste; // setter n sin neste peker til å peke på nåværende første element i listen
		forste = n; // setter så n til å være nåværende første element
		if (erTom()) {
			siste = n; // dersom listen er tom, vil siste peke på noden n og beholder denne som siste element
		}
		antall++;
	}
}
