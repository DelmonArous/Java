class TradFlett extends Thread {
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
	public TradFlett( Monitor m) {
		this.m = m; 		
	}
	
	public void run() {
		boolean noe = true;
		tab = m.taUt();
		String [] temparray;
		while(noe) {
			if (m.antall == 0) {
				m.settInn(tab);
				return;
			}

			temparray = m.taUt();
						
			tab = Flett(tab, temparray);
			if(tab.length == m.makslengde) {
				m.settInn(tab);
				noe = false;
			}
		}
		
	}
	
	/** Metode for sletting av to mindre tabeller
	 * 
	 * @param tab1 lokal, sortert tabell nr. 1
	 * @param tab2 lokal, sortert tabell nr. 2
	 * @return
	 */
	public String [] Flett(String [] tab1, String [] tab2){
		/** l er indeksteller for den resulterende, flettede tabellen 
		 * m er indeksteller for tabell 1, n er indeksteller for tabell 2 */
		int l = 0, m = 0, n = 0;
		String [] temp1 = tab1;
		String [] temp2 = tab2;
		String [] tempflett = new String[temp1.length + temp2.length];
	
		/* Her fletter vi saa lenge m og n er mindre enn lengden paa deres tilhorende tabeller */ 
		while(m < temp1.length && n < temp2.length){
			if (temp1[m].compareTo(temp2[n]) < 0) {
				tempflett[l] = temp1[m];
				m++;
				l++;
			}
			else {
				tempflett[l] = temp2[n];
				n++;
				l++;
			}
		}

		/* Skriver over resten av den ene delen av tabellen som ikke ble ferdig iterert over */
		if(m == temp1.length) { // dersom tabell nr. 1 ble ferdig iterert over
			while(n < temp2.length){
				tempflett[l] = temp2[n]; // skriv over
				n++;
				l++;				
			}
		}
		else if (n == temp2.length) { // dersom tabell nr. 2 ble ferdig iterert over
			while(m < temp1.length) {
				tempflett[l] = temp1[m]; // skriv over
				m++;
				l++;
			}
		}
		
		return tempflett;
	}
	
	
	
}
