/** Egendefinert unntak for naar dimensjonene paa brettet ikke blir lest inn riktig */
class FeilMedBrettUnntak extends Exception {

	/** Konstruktor skriver ut onsket feilmelding naar dimensjonene paa brettet ikke stemmer 
	 * 
	 * @param melding onsket feilmelding
	 * 
	 * */
	public FeilMedBrettUnntak(String melding) {
		super(melding); // kaller på superklassen Exception
		System.exit(1);
	}
	
}
