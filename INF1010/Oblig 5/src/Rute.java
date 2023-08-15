/** Gir informasjon om en rute i et brett */
abstract class Rute {
	/** Posisjonene til ruten i gitteret til brettet */
	private int i,j;
	/** Neste-pekeren til ruten til hoyre for denne ruten, i row-major order */
	private Rute neste;
	/** Peker til brettet for denne ruten */
	private Brett brett;
	/** Verdi for denne ruten */
	protected int verdi = 0;
	/** Peker til raden denne ruten befinner seg i */
	protected Rad rad;
	/** Peker til kolonnen denne ruten befinner seg i */
	protected Kolonne kol;
	/** Peker til boksen denne ruten befinner seg i */
	protected Boks boks;
	/** Boolean-setning som informerer om ruten er forhaandsutfylt eller ikke */
	protected boolean ertomrute;
	
	/** Konstuktor gir ruten tilhorende brett, rad, kolonne, boks og posisjon i brettet 
	 * 
	 * @param brett peker til Brett-objektet 
	 * @param rad peker til Rad-objektet ruten befinner seg i
	 * @param kol peker til Kolonne-objektet ruten befinner seg i
	 * @param boks peker til Boks-objektet ruten befinner seg i
	 * @param i rutens posisjon i i'te rad
	 * @param j rutens posisjon i j'te kolonne
	 * 
	 */
	public Rute(Brett brett, Rad rad, Kolonne kol, Boks boks, int i, int j) {
		this.brett = brett;
		this.rad = rad;
		this.kol = kol;
		this.boks = boks;
		this.i = i;
		this.j = j;
	}
	
	/** Setter opp nestepekeren til denne ruten ut ifra rutens posisjon (i,j) i gitteret. 
	 * Naar det finnes ingen rute rett til hoyre, setter metoden nestepekeren til aa peke paa ruten
	 * helt til venstre i neste rad osv.
	 */
	public void settNesteRute() {
		if (i <= (kol.hentLengde()-1) && j < (rad.hentLengde()-1)) {
			neste = rad.hentRute(j+1);
		}
		else if (i < (kol.hentLengde()-1) && j == (rad.hentLengde()-1)) {
			neste = rad.hentRute(0).hentKolonne().hentRute(i+1);
		}
		
		if(neste != null) {
			neste.settNesteRute(); // kun den nederste ruten til hoyre har nestepeker lik null
		}
	}
	
	/** Forsoker aa finne passende verdier for ruten, og dersom den lykkes kalles samme metode i neste rute.
	 * Naar et kall paa fyllUtRestenAvBrettet-metoden i neste rute returnerer, prover ruten neste tall 
	 * som enda ikke er provd, osv. helt til alle tall er provd i denne ruten. 
	 */
	public void fyllUtRestenAvBrettet() {
		if (ertomrute) { // sjekker om ruten er forhaandsutfylt
			for (int v = 1; v <= rad.hentLengde(); v++) { // looper gjennom alle mulige tall for denne rutens verdier
				if(this.settInnVerdi(v) && neste != null) { // dersom verdien er tillatt og denne ruten er ikke nederste rute helt til hoyre
					neste.fyllUtRestenAvBrettet(); // forsoker aa fulle neste rutes verdi
					if(neste.erTomRute()){ // naar vi returnerer fra kallet i neste rute, nullstiller vi dens verdien
						neste.tilbakestillVerdi();
					}
				}
				else if (this.settInnVerdi(v) && neste == null) { // befinner oss naa i nederste rad helt til venstre og forsoker aa gi denne siste ruten en verdi
					// vi kloner brettet, da vi naa har en losning temp
					Brett temp = new Brett(brett.hentAntRadBoks(), brett.hentAntKolBoks());
					for (int i = 0; i < brett.hentAntRad(); i++) {
						for (int j = 0; j < brett.hentAntKol(); j++) {
							temp.settInnRute(new UtfyltRute(temp, brett.hentRute(i, j).hentRuteVerdi(), 
									temp.hentRad(i),temp.hentKolonne(j), 
									temp.hentBoks(i/temp.hentAntRadBoks(), j/temp.hentAntKolBoks()),i,j), i, j);	
						}
					}
					brett.hentSudokuBeholder().settInn(temp); // plasserer losningen (utfylt brett) i beholderen til brettet
				}
			}
		}
		else {
			if(neste != null) {
				neste.fyllUtRestenAvBrettet();
				if(neste.erTomRute()){
					neste.tilbakestillVerdi();
				}
			}
			else {
				Brett temp = new Brett(brett.hentAntRadBoks(), brett.hentAntKolBoks());
				for (int i = 0; i < brett.hentAntRad(); i++) {
					for (int j = 0; j < brett.hentAntKol(); j++) {
						temp.settInnRute(new UtfyltRute(temp, brett.hentRute(i, j).hentRuteVerdi(), 
								temp.hentRad(i),temp.hentKolonne(j), 
								temp.hentBoks(i/temp.hentAntRadBoks(), j/temp.hentAntKolBoks()),i,j), i, j);	
					}
				}
				brett.hentSudokuBeholder().settInn(temp);
			}
		}
	}
	
	/** Metoden blir overskrevet i tilhorende subklasse */
	public void tilbakestillVerdi() {}

	/** Metoden sjekker om verdien v er tillatt i ruten 
	 *
	 * @param v verdi-sjekk for ruten 
	 * @return false gaar ut ifra at alle ruter ikke kan fylles til og begynne med
	 * 
	 */
	public boolean settInnVerdi(int v) {
		return false;
	}
	
	/** Henter verdien i selve ruten 
	 * 
	 * @return verdi returnerer verdien til ruten
	 * 
	 */
	public int hentRuteVerdi() {
		return verdi;
	}
	
	/** Henter raden ruten er plassert i
	 * 
	 *  @return rad returnerer rutens rad
	 *  
	 */
	public Rad hentRad() {
		return rad;
	}
	
	
	/** Henter kolonnen ruten er plassert i
	 * 
	 *  @return kol returnerer rutens kolonne
	 *  
	 */
	public Kolonne hentKolonne() {
		return kol;
	}
	
	/** Henter boksen ruten er plassert i
	 * 
	 *  @return boks returnerer rutens boks
	 *  
	 */
	public Boks hentBoks() {
		return boks;
	}
	
	/** Henter rutens nestepeker, altsaa ruten til hoyre
	 * 
	 *  @return neste returnerer ruten rett til hoyre for denne ruten
	 *  
	 */
	public Rute hentNesteRute(){
		if(neste != null){
			return neste;
		} 
		else{
			return this;
		}
	}
	
	
	/** Sjekker om ruten er forhaandsutfylt eller ikke
	 * 
	 * @param ertomrute sjekker om ruten tilhorer subklassen TomRute eller UtfyltRute
	 *  
	 */
	public boolean erTomRute(){
		return ertomrute;
	}
}
