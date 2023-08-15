import java.io.*;

/** Gir informasjon om hvordan den sorterte tabellen skrives ut */
class Skriver {
	/** Dette er utskrivnings-objektet */
	private PrintWriter utfil;
	/** Teller brukes til utskrivning */
	private int teller;
	
	/** Konstruktor tar inn en sortert tabell av ord, og skriver ut disse til filnavn
	 * 
	 * @param antOrd angitt lengde paa tabellen
	 * @param tab sortert tabell med innhold av ord
	 * @param filnavn den sorterte tabellen skrives ut til fil med filnavn "filnavn"
	 * @throws FeilMedAntOrd egendefinert unntak
	 * 
	 */
	public Skriver(int antOrd, String [] tab, String filnavn) throws FeilMedAntOrd {
		
		/* Sjekker om at tabellen har riktig lengde */
		if (antOrd != tab.length) {
			throw new FeilMedAntOrd("Den sorterte tabellen har ikke riktig lengde.");
		}

		try {
			utfil = new PrintWriter(filnavn);
			
			teller = 0;
			while (teller < antOrd) {
				/* Sjekker om at tabellen inneholder null-pekere */
				if (tab[teller] == null) {
					throw new FeilMedAntOrd("Det ligger en null-peker som element i den sorterte tabellen.");
				}
				
				utfil.println(tab[teller]);
			
				teller++;
			}
			
			utfil.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}		
	}
		
}