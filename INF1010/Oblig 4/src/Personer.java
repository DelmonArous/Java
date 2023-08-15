class Personer {
	private String navn, kjonn; // personen har navn og kj�nn
	private int idnummer; // personen har ogs� et unikt nummer
	private EnkelReseptListe reseptbeholder; // en liste over resepter denne personen har
		
	public Personer(int persNr, String n, String k) {
		idnummer = persNr;
		navn = n;
		kjonn = k;
		reseptbeholder = new YngsteForstReseptListe();
	}
	
	public String hentNavn() {
		return navn;
	}
	
	public String hentKj�nn() {
		return kjonn;
	}
	
	public int hentIDnummer() {
		return idnummer;
	}
	
	public void leggInnResept(Resepter r) {
		reseptbeholder.leggInn(r);
	}
	
	public EnkelReseptListe hentReseptbeholder() {
		return reseptbeholder;
	}
}