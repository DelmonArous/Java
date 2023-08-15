
class Trad extends Thread {
	/** Peker til bufferen */
	private Monitor m;
	/** Lokal tabell for denne traaden */
	private String [] tab;
	
	/** Konstruktor for traaden henter inn hele tabellen og lager en lokal tabell som skal sorteres
	 * 
	 * @param tabell dette er hele tabellen
	 * @param startInd lokal startindeks paa sorteringsomraadet
	 * @param sluttInd lokal sluttindeks paa sorteringsomraadet
	 * @param m peker til monitoren (bufferen vaar)
	 */
	public Trad(String [] tabell, int startInd, int sluttInd, Monitor m) {
		this.m = m; 
		tab = new String[sluttInd - startInd + 1];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = tabell[i + startInd];
		}		
	}
	
	public void run() {
		String temp;
		
		/* Vaar egen sorteringsalgoritme som blir anvendt av hver traad paa deres lokale tabeller */
		for (int i = 0; i < tab.length - 1; i++) {
			for (int j = i+1; j < tab.length; j++ ) {
				if (tab[i].compareTo(tab[j]) > 0) {
					temp = tab[i];
					tab[i] = tab[j];
					tab[j] = temp;
				}
			}		
		} // ferdig med aa sortere lokal tabell
		
		m.settInn(tab);		
		
	}
	
}
