package oblig2;

class Bok {
	private String forfatter;
	private String tittel;
	private int aarstall;
	
	public Bok(String f, String t, int tall) {
		forfatter = f;
		tittel = t;
		aarstall = tall;
	}
	
	public String hentForfatter() {
		return forfatter;
	}

	public String hentTittel() {
		return tittel;
	}
	
	public int hentAarstall() {
		return aarstall;
	}
	
	public void endreForfatter(String f) {
		forfatter = f;
	}
	
	public void endreTittel(String t) {
		tittel = t;
	}
	
	public void endreAarstall(int t) {
		aarstall = t;
	}
}
