/*
 * YngsteForstReseptListe er en subklasse av EnkelReseptListe.
 * Den eneste metoden denne subklassen overkj�rer er metoden
 * som setter inn en resept. Her kommer reseptene som blir lagt inn
 * f�rst, fremst i listen.
 */

class YngsteForstReseptListe extends EnkelReseptListe {
	
	public void leggInn(Resepter r) {
		Node n = new Node(r);
		n.neste = forste; // setter n sin neste peker til � peke p� n�v�rende f�rste element i listen
		forste = n; // setter s� n til � v�re n�v�rende f�rste element
		if (erTom()) {
			siste = n; // dersom listen er tom, vil siste peke p� noden n og beholder denne som siste element
		}
		antall++;
	}
}
