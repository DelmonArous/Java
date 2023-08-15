import java.util.Scanner;
import java.io.*;
/** Gir informasjom om hvordan ordene i tekstfilen leses inn */
class Leser {
	/** Skanne-objektet vaart */
	private Scanner scan;
	/** Variablene er hhv. antall traader, antall ord i tekstfilen og en teller */
	private int antTraader, antOrd, teller, N, N_rest;
	/** Dette er tabellen som skal sorteres */
	private String [] tab;
	/** Filnavn til tekstfilen som skal leses inn og skrives ut */
	private String innfil, utfil;
	
	/** Antall traader, navnet paa innlesningsfilen og utskrivningsfilen leses inn.
	 *  Samtidig blir leses inn resten av filen som inneholder ordene inn i tab.
	 * 
	 * @param args filnavnet paa innlesningsfilen og utskrivningsfilen
	 * @throws FeilMedAntOrd feil med antall ord i innlesningsfilen
	 * 
	 */
	public Leser(String [] args) throws FeilMedAntOrd {
		antTraader = Integer.parseInt(args[0]);
		innfil = args[1];
		utfil = args[2];
		
		try {
			scan = new Scanner(new File(innfil));
			
			antOrd = scan.nextInt(); // leser inn antall ord det er totalt i resten av filen
			scan.nextLine();
			
			tab = new String[antOrd]; // angir lengden av tabellen
			
			teller = 0;
			/* Leser forst alle ordene inn i tabellen i minnet */
			while (scan.hasNextLine()) {				
				tab[teller] = scan.nextLine();
				teller++;
			}
			
			/* Sjekker om antall ord i innfilen stemmer med heltallet på forste linje */
			if (teller != antOrd) {
				throw new FeilMedAntOrd("Antall ord i innfilen ikke stemmer med heltallet på forste linje.");
			}
			
			scan.close();
			
		} // try slutt
		catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Dette er bufferen vaar */
		Monitor m = new Monitor(antOrd, utfil);
		
		/* Lag og start antTraader antall traader */
		N = antOrd/antTraader; // heltallsdivisjon, saa resultatet blir trunkert nedover
		N_rest = antOrd % antTraader;
		
		for (int i = 0; i < antTraader; i++) {
			if (i < N_rest) {
				new Trad(tab, i*(N+1), (i+1)*(N+1) - 1, m).start();
			}
			else {
				new Trad(tab, i*N + N_rest, (i+1)*N + N_rest - 1, m).start();
			}
		}
		//m.ventPaaAlleFerdig(antTraader);
		for (int i = 0; i < 10; i++) {
			new TradFlett(m).start();
		}
		
		
		
		
	} // konstruktor slutt
} // klasse slutt