/** Gir informasjon om  et tomt Rute-objekt */
class TomRute extends Rute {
	
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
	public TomRute(Brett brett, Rad rad, Kolonne kol, Boks boks, int i, int j) {
		super(brett, rad, kol, boks, i, j);
		ertomrute = true;
	}
	
	/** Forsoker aa sette inn en verdi i den det tomme Rute-objektet 
	 * 
	 * @param v verdi som forsokes i ruten
	 * @return true dersom verdien v er tillat, ikke eksisterer i rutens rad, kolonne eller boks
	 * @return false dersom verdien v finnes i rutens rad, kolonne eller boks
	 * 
	 */
	public boolean settInnVerdi(int v) {
		verdi = v;
		if(rad.erGyldig(this) && kol.erGyldig(this) && boks.erGyldig(this))
			return true;
		else {
			verdi = 0;
			return false;
		}
	}
	
	/** Tilbakestiller rutens verdi */
	public void tilbakestillVerdi() {
		verdi = 0;
	}
	
}