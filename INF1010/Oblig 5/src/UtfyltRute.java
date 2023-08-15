/** Gir informasjon om  et utfylt Rute-objekt */
class UtfyltRute extends Rute {
	
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
	public UtfyltRute(Brett brett, int verdi, Rad rad, Kolonne kol, Boks boks, int i, int j) {
		super(brett, rad, kol, boks, i, j);
		this.verdi = verdi;
		ertomrute = false;
	}
	
}
