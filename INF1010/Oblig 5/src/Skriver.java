import java.io.*;

/** Gir informasjon om hvordan losningene skrives ut */
class Skriver {
	/** Dette er utskrivnings-objektet */
	private PrintWriter utfil;
	/** Tellere som brukes til utskrivning */
	private int losningteller, teller;
	/** Forste variabel er oppgavebrettet som inneholder alle losningene, og temp er midlertidig losning paa brettet */
	private Brett brett, temp;
	
	/** Konstruktor tar inn brettet som inneholder alle losningene, og skriver alle sammen ut 
	 * (i et bestemt format) til filnavn
	 * 
	 * @param brett oppgavebrettet med sudokubeholder
	 * @param filnavn losningene på oppgavebrettet skrives inn paa denne filen 
	 * 
	 */
	public Skriver(Brett brett, String filnavn) {
		this.brett = brett;
		
		try {
			utfil = new PrintWriter(filnavn);
			
			teller = 1;
			losningteller = brett.hentSudokuBeholder().hentAntallLosninger();
			/* Itererer saa lenge det finnes flere losninger i losningsbeholderen til brettet */
			while (losningteller > 0) {
				temp = brett.hentSudokuBeholder().hentBrett(teller-1); // tar ut en losning, som temp peker til
				utfil.print(teller + ": ");
				for (int i = 0; i < brett.hentAntRad(); i++) {
					for (int j = 0; j < brett.hentAntKol(); j++) {
						/* Henter verdien i Rute-objektet med posisjon (i,j) og skriver den ut */
						utfil.print(temp.hentRute(i,j).hentRuteVerdi());
					}
					utfil.print("//");
				}
				utfil.println();
			
				teller++;
				losningteller--;
			}
			
			utfil.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
		
}