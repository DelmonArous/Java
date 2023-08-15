package oblig2;

class Plate {
	private String artistnavn;
	private String tittel;
	private int antallspor;
	
	public Plate(String a, String t, int ant) {
		artistnavn = a;
		tittel = t;
		antallspor = ant;
	}
	
	public String hentArtist() {
		return artistnavn;
	}

	public String hentTittel() {
		return tittel;
	}
	
	public int hentAntallSpor() {
		return antallspor;
	}
	
	public void endreArtist(String a) {
		artistnavn = a;
	}
	
	public void endreTittel(String t) {
		tittel = t;
	}
	
	public void endreAntallSpor(int ant) {
		antallspor = ant;
	}
}
