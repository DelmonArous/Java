import java.util.Scanner;
import java.io.*;
import javax.swing.SwingUtilities;

/** Klassen leser inn en txt-fil og lager et JFrame-objekt ut fra innlesningen */
class Leser {
	/** Skanne-objektet vaart */
	private Scanner scan;
	/** Antall rader og kolonner per boks, og en normal teller */
	private int rader, kolonner, teller;
	/** Initialiserer oppgavebrettet */
	private Brett brett;
	/** Filnavn */
	private String filnavn;
	
	/** Storrelsen paa brettet og selve oppgaven (de sifrene som alt er fylt inn) leses inn fra fil. 
	 * 
	 * @param args filnavnet paa innlesningsfilen og utskrivningsfilen
	 * 
	 */
	public Leser(String [] args) {
		filnavn = args[0];
		
		try {
			scan = new Scanner(new File(filnavn));
			rader = scan.nextInt(); // leser inn antall rader per boks
			scan.nextLine();
			kolonner = scan.nextInt(); // leser inn antall kolonner per boks
			scan.nextLine();
			
			brett = new Brett(rader, kolonner); // oppretter brettet med riktig storrelse	
			
			teller = 0; // radnummeret som teller
			while (scan.hasNext()) {
				char[] linje = scan.nextLine().toCharArray(); // leser inn en hel linje og konverterer til char i en tabell
				for (int j = 0; j < rader*kolonner; j++) { // kolonnenummeret j
					if (linje[j] == '.') { // dersom ruten er tom, lager vi oss et TomRute-objekt i brettet i posisjon (teller,j)
						brett.settInnRute(new TomRute(brett, brett.hentRad(teller),brett.hentKolonne(j),
								brett.hentBoks(teller/rader, j/kolonner),teller,j), teller, j); 
					}
					else { // dersom ruten er utfylt, lager vi oss et UtfyltRute-objekt i brettet i posisjon (teller,j)
						brett.settInnRute(new UtfyltRute(brett, Character.getNumericValue(linje[j]), 
								brett.hentRad(teller),brett.hentKolonne(j), brett.hentBoks(teller/rader, j/kolonner),teller,j), teller, j);
					}
				}
				teller++;
			} // while slutt
			
			if (teller != rader*kolonner) {
				throw new FeilMedBrettUnntak("Det mangler en rad i filen.");
			}
		} // try slutt
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		catch (FeilMedBrettUnntak e) {
			e.getMessage();
		}
		
		/* Setter opp alle nestepekerne til alle rutene i brettet. Vi begynner med ruten helt overst til venstre */
		brett.hentRute(0,0).settNesteRute();
		/* Fyller ut oppgavebrettet med alle mulige losninger paa en brute-force metode */
		brett.fyllUtBrett();
		
		/* Dersom det oppgis to filnavn paa kommandolinjen, skrives losningene inn i det andre filnavnet */
		if (args.length > 1) {
			new Skriver(brett, args[1]);
		}
		
		/* Oppretter en traad og lager oss et JFrame-objekt for oppgaven */
		SwingUtilities.invokeLater(
				new Runnable() {
					public void run() {
						new GUI(brett);
						}
				});
		
	} // konstruktor slutt
} // klasse slutt
