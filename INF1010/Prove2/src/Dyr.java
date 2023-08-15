abstract class Dyr {
	private String navn;
	private int alder;
	
	public Dyr(String navn, int alder) {
		this.navn = navn;
		this.alder = alder;
	}
	
	public String hentNavn() {return navn;}
	
	public int hentAlder() {return alder;}
	
}
