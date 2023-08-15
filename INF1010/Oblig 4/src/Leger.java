/*
 * Objekter av Leger skal kunne sorteres alfabetisk etter navn, og man
 * skal kunne finne en lege basert på navn. Klassen Leger implementere derfor
 * grensesnittene Comparable (med seg selv) og Lik.
 */

class Leger implements Comparable<Leger>, Lik, Avtalelege {
	private String navn;
	private int spesialist;
	private int avtalenummer;
	private EnkelReseptListe reseptbeholder; // en liste over resepter en lege har skrevet ut
	
	public Leger(String n, int s, int a) {
		navn = n;
		spesialist = s;
		avtalenummer = a;
		reseptbeholder = new EldsteForstReseptListe();
	}
	
	public String hentNavn() {
		return navn;
	}
	
	// En lege er en spesialist dersom spesialist = 1
	public boolean erSpesialist() {
		if (spesialist == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Sjekker om navnet til legen er lik parameteren til metoden samme
	public boolean samme(String s) {
		if (s.equals(navn)) {
			return true;
		}
		else {
			return false;
		}
	}

	// Dette er en metode fra grensesnittet Comparable.
	// returnerer negativt dersom navnet til denne legen kommer før navnet til legen i parameteren
	// returnerer positivt dersom navnet til denne legen kommer etter navnet til legen i parameteren
	// returnerer 0 dersom navnet til legen i parameteren er lik navnet til denne legen
	public int compareTo(Leger l) {
		return navn.compareTo(l.hentNavn());
	}
	
	public int hentAvtalenummer() {
		return avtalenummer;
	}
	
	public void leggInnResept(Resepter r) {
		reseptbeholder.leggInn(r);
	}
	
	public EnkelReseptListe hentReseptbeholder() {
		return reseptbeholder;
	}
}