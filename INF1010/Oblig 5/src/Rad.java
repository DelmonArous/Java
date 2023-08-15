/** Gir informasjon om en hel rad i et brett */
class Rad extends SubBrett {
	
	/** Konstruktor setter opp lengden paa tabellen til Rad-objektet 
	 * 
	 * @param antkol antall Rute-objekter det er i en rad
	 * 
	 */
	public Rad(int antkol) {
		super(antkol);
		obj = new Rute[antkol];
	}

}