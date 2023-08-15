/** Egendefinert unntak */
class FeilMedAntOrd extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Konstruktor skriver ut onsket feilmelding naar antall ord i innlesningsfilen
	 *  ikke stemmer med angitt antall ord i filen
	 * 
	 * @param melding onsket feilmelding
	 * 
	 * */
	public FeilMedAntOrd(String melding) {
		super(melding); // kaller på superklassen Exception
		System.exit(1);
	}

}