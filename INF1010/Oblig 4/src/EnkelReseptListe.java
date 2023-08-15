import java.util.Iterator;

/*
 * Klassen EnkelReseptListe inneholder en envisliste
 * med en peker til første og en peker til siste element i listen.
 * Disse elemtene (reseptene) skal kunne være med i flere objekter av denne klassen.
 */

class EnkelReseptListe {
	protected Node forste, siste; // peker til første og siste element i listen
	protected int antall; // antall elementer i listen
	
	protected class Node {
		Resepter obj; // objekt av type Resepter
		Node neste; // peker til neste element i listen med sin egen Resepter obj (enveisliste)
		
		Node (Resepter r) {
			obj = r;
		}
	}
	
	public EnkelReseptListe() { // har verken listehode eller listehale
		forste = null;
		siste = null;
		antall = 0;
	}
	
	// Metode som sjekker om listen er tom eller ikke
	public boolean erTom() {
		return (forste == null); // er sann dersom første element i listen er null
	}
	
	// Metode for å sette inn en resept i listen
	public void leggInn(Resepter r) {} // denne metoden blir overkjørt i dens tilhørende subklasser
	
	// Finner en resept basert på reseptnummeret
	public Resepter finnResept(int idnummer) throws Exception {
		Node n = forste; // vi begynner med det første elementet i listen, som er det øverste elementet i stabelen
		boolean funnet = false;
		
		if (!erTom()) { // sjekker om listen ikke er tom for å iterere gjennom elementene i listen
			for (int i = antall; i > 0; i--) { // itererer gjennom listen
				if (n.obj.hentIDnummer() == idnummer) { // dersom Node n har resept som er lik idnummer
					funnet = true;						// returnerer vi denne resepten
					return n.obj;
				}
				else { // ellers itererer vi oss til neste element i listen og sjekker for denne
					n = n.neste;
				}
			}
		}
		
		// // hvis resepten som det letes etter ikke finnes i listen, kastes det et unntak
		if (funnet == false) {
			throw new Exception("Resept med nummer " + idnummer + " finnes ikke i resept-beholderen!");
		}
		
		return null;
	}
	
	// Denne metoden returnerer en Iterator over listen
	public Iterable<Resepter> itererGjennomAlle() {
		return new Ukjent1();
	}
	
	class Ukjent1 implements Iterable<Resepter> { 
		public Iterator<Resepter> iterator() {return new Ukjent2();} 
	}
	
	class Ukjent2 implements Iterator<Resepter> { 
		int teller = 0;
		Node n = forste;
		public boolean hasNext() {
			return (teller < antall);
		}
		public Resepter next() {
			teller++;
			Node etter = n;
			n = n.neste;
			return etter.obj;
		}
		public void remove() {}
	}
}
