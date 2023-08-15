/** Gir informasjon om en hel kolonne i et brett */
class Kolonne extends SubBrett {
	
	/** Konstruktor setter opp lengden paa tabellen til Kolonne-objektet 
	 * 
	 * @param antrad antall Rute-objekter det er i en kolonne
	 * 
	 */
	public Kolonne(int antrad) {
		super(antrad);
		obj = new Rute[antrad];
	}	
	
}
