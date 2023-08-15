/** Gir informasjon til Rad-, Kolonne- og Boks-objekter */
abstract class SubBrett {
	/** Definerer lengden paa tabellen */
	private int lengde;
	/** Hvert objekt skal vaere en tabell */
	protected Rute [] obj;
	
	/** Konstruktor gir lengde paa tabellen til obj
	 * 
	 *  @param lengde definerer lengden paa tabellen
	 *  
	 *  */
	public SubBrett(int lengde) {
		this.lengde = lengde;
		obj = new Rute[lengde];
	}
	
	/** Plasserer ruten inn i objektet-tabellen obj i den i'te posisjonen 
	 * 
	 * @param r ruten som plasseres i tabellen
	 * @param i rutens i'te posisjon i tabellen 
	 * 
	 */
	public void settInnRute(Rute r, int i) {
		obj[i] = r;
	}
	
	/** Sjekker om det er lovlig aa plassere onsket rute i tabellen obj 
	 * 
	 * @param r ruten som skal plasseres i tabellen
	 * @return false dersom rutens verdi allerede eksisterer i tabellen
	 * @return true dersom rurens verdi ikke eksisterer i tabellen
	 */
	public boolean erGyldig(Rute r) {
		for (int i = 0; i < lengde; i++) {
			if (r.hentRuteVerdi() == obj[i].hentRuteVerdi() && r != obj[i]) {
				return false;
			}
		}
		return true;
	}
	
	/** Henter en rute fra tabellen obj 
	 * 
	 * @param i rutens i'te posisjon i tabellen
	 * @return obj[i] returnerer ruten i den i'te posisjonen i tabellen
	 * 
	 */
	public Rute hentRute(int i) {
		return obj[i];
	}
	
	/** Henter indeksen til ruten i tabellen 
	 * 
	 * @param r ruten i tabellen
	 * @return i rutens indeks i tabellen dersom den finnes
	 * @return dersom ruten ikke finnes i tabellen
	 * 
	 */
	public int hentRuteIndeks(Rute r) {
		for (int i = 0; i < lengde; i++) {
			if (obj[i] == r) {
				return i;
			}
		}
		return 0;
	}
	
	/** Henter tabellens lengde 
	 * 
	 * @return lengde returnerer tabellens lengde
	 * 
	 */
	public int hentLengde() {
		return lengde;
	}
	
}
