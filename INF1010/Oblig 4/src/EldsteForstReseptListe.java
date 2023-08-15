/*
 * EldsteForstReseptListe er en subklasse av EnkelReseptListe.
 * Den eneste metoden denne subklassen overkjører er metoden
 * som setter inn en resept. Her kommer reseptene som blir lagt inn
 * sist, fremst i listen.
 */

class EldsteForstReseptListe extends EnkelReseptListe {
	
	public void leggInn(Resepter r) {
		Node n = new Node(r);
		if (erTom()) {
			forste = n; // dersom listen er tom, vil forste peke på node n og beholde den som første elemnt i listen
		}
		else {
			siste.neste = n; // dersom listen ikke er tom, vil siste sin neste-peker peke på node n
		}
		siste = n; // her setter vi inn de elemente som kommer først, til å være de siste elementene i listen
		antall++;
	}
	
}
