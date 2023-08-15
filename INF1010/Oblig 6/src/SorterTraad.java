class SorterTraad implements Runnable {
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
	public SorterTraad(String [] tabell, int startInd, int sluttInd, Monitor m) {
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
		
		try {
			bufferFlett(); // rekursiv fletting
		} catch (FeilMedAntOrd e) {
			e.printStackTrace();
		}
		
	}
	
	public void bufferFlett() throws FeilMedAntOrd {
		String [] temparray;
		
		/* Denne traaden faar en midlertidig array dersom det ligger en lokal, sortert array i bufferet */
		temparray = m.Buffer(tab);
		if (temparray != null) {
			tab = Flett(tab, temparray); // denne traaden fletter disse to lokale tabellene, sin egen og den midlertidige
			bufferFlett();
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