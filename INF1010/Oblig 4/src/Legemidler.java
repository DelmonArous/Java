/*
 * Superklassen Legemidler implementerer metodene i grensesnittet Legemiddel.
 */

abstract class Legemidler implements Legemiddel{     
	private String navn;
	private int idnummer, mengde;
	private double pris;
	
	public Legemidler(int iNr, String n, double p, int m){
		idnummer = iNr;
		navn = n;
		pris = p;
		mengde = m;
	}
	
	public int hentIDnummer() {
		return idnummer;
	}
	
	public String hentLegemiddelNavn() {
		return navn;
	}
	
	public double hentPris() {
		return pris;
	}
	
	public void endrePris(double p) {
		pris = p;
	}
	
	public int hentMengde() {
		return mengde;
	}	
}