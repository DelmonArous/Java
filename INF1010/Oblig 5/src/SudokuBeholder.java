/** Gir informasjon om lenket liste-beholder av losninger */
class SudokuBeholder {
	/** Pekeren til forste element i listen */
	private Node forste;
	/** Tellere for antall losninger i beholderen og totalt antall losninger av oppgaven */
	private int antall, totantlosninger;
	/** Beholderens maks kapasitet for antall losninger */
	private int maksantlosninger = 750;
	
	/** Indre klasse som inneholder peker til neste element i listen og et objekt av type Brett (losning) */
	private class Node {
		Node neste;	
		Brett obj;
		
		Node(Brett obj) {
			this.obj = obj;
		}
		
	}
	
	/** Sjekker om beholderen er tom
	 * 
	 *  @return true dersom forste element er lik null
	 *  @return false dersom forste element er ulik null
	 *  
	 */
	public boolean erTom() {
		return (forste == null);
	}
	
	/** Setter inn en losning i listen, LIFO 
	 * 
	 * @param b losningen av oppgaven
	 * 
	 */
	public void settInn(Brett b) {
		Node n = new Node(b);
		if (antall < maksantlosninger) {
			if(!erTom()) {
				n.neste = forste;
			}
			forste = n;
			antall++;
		}
		totantlosninger++;
	}
	
	
	/** Henter (uten aa fjerne fra beholderen) en losning 
	 * 
	 * @param i den i'te losningen i beholderen 
	 * @return obj returnerer den i'te losningsbrettet i beholderen
	 * @return null dersom indeksen er storre enn antall losninger i beholderen
	 * 
	 */
	public Brett hentBrett(int i) {
		Node n = forste;
		if(!erTom() && i < antall) {
			for (int j = 0; j <= i; j++) {
				Brett obj = n.obj;
				n = n.neste;
				if (j == i) {	
					return obj;
				}
			}
		}
		return null;
	}
	
	/** Tar ut og returnerer et losningsbrett fra beholderen 
	 * 
	 * @return obj returnerer det fremste elementet i listen
	 * @return null dersom listen er tom
	 * 
	 */
	public Brett taUt() {
		if(!erTom()) {
			Brett obj = forste.obj;
			forste = forste.neste;
			antall--;
			return obj;
		}
		return null;
	}
	
	/** Henter totalt antall losninger av oppgavebrettet 
	 * 
	 * @return totantlosninger totalt antall losninger av oppgavebrettet 
	 * 
	 */
	public int hentAntallLosninger() {
		return totantlosninger;
	}
	
	/** Henter antall losninger av oppgavebrettet igjen i beholderen 
	 * 
	 * @return antall antall losninger av oppgavebrettet igjen i beholderen 
	 * 
	 */
	public int hentAntallLosningerIBeholder() {
		return antall;
	}
}