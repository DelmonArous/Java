class Resepter {
	private int idnummer, reit; // hver resept har eget idnummer som starter på null, og en reit som beskriver hvor mange ganger resepten kan gjenbrukes
	private String legenavn, farge; // navnet på legen som har skrevet ut denne resepten og hvilke farge resepten har
	private int personnummer, legemiddelnummer; // navn på personnummeret til personen som har denne resepten og legemiddelnummeret for denne resepten
	private Leger lege; // peker til legen som har skrevet ut denne resepten
	private Legemidler medisin; // peker til legemiddelet for denne resepten
	
	public Resepter(int reseptNr, String frg, int personNum, String legeNvn, int lenum, int r){
		idnummer = reseptNr;
		farge = frg;
		personnummer = personNum;
		legenavn = legeNvn;
		legemiddelnummer = lenum;
		reit = r;
	}
	
	public int hentIDnummer() {
		return idnummer;
	}
	
	public String hentFarge() {
		return farge;
	}
	
	public int hentPersonNummer() {
		return personnummer;
	}
	
	public String hentLegeNavn() {
		return legenavn;
	}
	
	public int hentLegemiddelNummer() {
		return legemiddelnummer;
	}
	
	// Metoden reduserer reit med 1 når den brukes. Dette kun når reit > 0
	public int brukResept() {
		if (reit != 0) {
			return reit--;
		}
		return reit;
	}
	
	// Metoden sjekker om resepten kan brukes, altså er gyldig ved å se om reit = 0
	public boolean gyldigResept() {
		if (reit == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int hentReit() {
		return reit;
	}
	
	public void settInnLege(Leger l){
		lege = l;
	}
	
	public Leger hentLege(){
		return lege;
	}
	
	public void settInnLegemiddel(Legemidler lm){
		medisin = lm;
	}
	
	public Legemidler hentLegemiddel(){
		return medisin;
	}
}
