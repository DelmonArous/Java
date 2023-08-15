/** Gir informasjon om en boks */
class Boks extends SubBrett {
	/** Antall rader og kolonner en bok inneholder, hhv. */
	private int antrad, antkol;
	
	/** Setter opp en boks som en en-dimensjonal tabell med antrad*antkol antall Rute-objekter */
	public Boks(int antrad, int antkol) {
		super(antrad*antkol); // kaller på superklassen SubBrett
		this.antrad = antrad;
		this.antkol = antkol;
		obj = new Rute[antrad*antkol]; // Row-major order
	}
	
	/** Returnerer antall kolonner det er i en boks */
	public int hentAntKol() {
		return antkol;
	}
	
	/** Returnerer antall rader det er i en boks */
	public int hentAntRad() {
		return antrad;
	}
	
}
