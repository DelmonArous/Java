/** Gir informasjon om selve Sudoku-brettet */
class Brett {
	/** To-dimensjonal Rute-tabell som skal inneholde alle Rute-objektene i spillet */
	private Rute [][] brett;
	/** En-dimensjonal Kolonne-tabell som skal inneholde alle Kolonne-objektene i brettet */
	private Kolonne [] allekolonner;
	/** En-dimensjonal Rad-tabell som skal inneholde alle Rad-objektene i brettet */
	private Rad [] allerader;
	/** To-dimensjonal Boks-tabell som skal inneholde alle Boks-objektene i brettet */
	private Boks [][] allebokser; 
	/** Antall rader og kolonner det er i brettet, og antall rader og kolonner det er i en boks */
	private int antrad, antkol, antradboks, antkolboks;
	/** Hvert brett har en beholder av utfylte brett (losninger) */
	private SudokuBeholder beholder = new SudokuBeholder(); 
	
	/** Konstruktoren tar inn dimensjonene til en boks og setter opp dimensjonene på brettet ut fra dette 
	 * 
	 * @param antradboks antall rader i en bok
	 * @param antkolboks antall kolonner i en boks
	 * 
	 */
	public Brett(int antradboks, int antkolboks) {
		this.antradboks = antradboks;
		this.antkolboks = antkolboks;
		antrad = antradboks*antkolboks;
		antkol = antrad;
		brett = new Rute[antrad][antkol];		
		allekolonner = new Kolonne[antrad];
		allerader = new Rad[antkol];
		allebokser = new Boks[antkolboks][antradboks];
		
		// Setter opp tabell-lengden på alle Rad- og Kolonne-objektene i de nestede tabellene allerader og allekolonner
		for (int i = 0; i < antrad; i++) {
			allerader[i] = new Rad(antkol);
			allekolonner[i] = new Kolonne(antrad);
		}
		// Setter opp tabell-lengden på alle Boks-objektene i den nestede tabellen allebokser
		for (int i = 0; i < antkolboks; i++) {
			for(int j = 0; j< antradboks; j++){
				allebokser[i][j] = new Boks(antradboks, antkolboks);		
			}	
		}

	}

	/** Plasserer et Rute-objekt i brett, og i tilhorende rad, kolonne og boks
	 * 
	 * @param r Rute-objektet som plasseres i brettet
	 * @param i verdi for radposisjon
	 * @param j verdi for kolonneposisjon
	 * 
	 */
	public void settInnRute(Rute r, int i, int j) {
		brett[i][j] = r;
		allerader[i].settInnRute(r, j);
		allekolonner[j].settInnRute(r, i);
		allebokser[i/antradboks][j/antkolboks].settInnRute(r, (i%antradboks)*antkolboks + j%antkolboks);
	}

	/** Henter onsket Rute-objekt fra brettet 
	 * 
	 * @param i verdi for radposisjon til Rute-objektet
	 * @param j verdi for kolonneposisjon til Rute-objektet
	 * @return brett[i][j] returnerer Rute-objektet med posisjon (i,j) i brettet
	 * 
	 */
	public Rute hentRute(int i, int j) {
		return brett[i][j];
	}
	
	/** Henter onsket Rad-objekt fra brettet 
	 * 
	 * @param i verdi for i'te rad i brettet
	 * @return allerader[i] returnerer den i'te raden i brettet
	 * 
	 */
	public Rad hentRad(int i) {
		return allerader[i];
	}
	
	/** Henter onsket Kolonne-objekt fra brettet 
	 * 
	 * @param j verdi for j'te kolonne i brettet
	 * @return allekolonner[j] returnerer den j'te kolonnen i brettet
	 * 
	 */
	public Kolonne hentKolonne(int j) {
		return allekolonner[j];
	}
	
	/** Henter onsket Boks-objekt fra brettet 
	 * 
	 * @param i verdi for radposisjonen til boksen i brettet
	 * @param j verdi for kolonneposisjonen til boksen i brettet
	 * @return allebokser[i][j] returnerer boksen i brettet med posisjon (i,j)
	 * 
	 */
	public Boks hentBoks(int i, int j) {
		return allebokser[i][j];
	}
	
	/** Henter antall rader i brettet 
	 * 
	 * @return antrad returnerer antall rader i brettet
	 * 
	 */
	public int hentAntRad() {
		return antrad;
	}

	/** Henter antall rader i en boks i brettet 
	 * 
	 * @return antradboks returnerer antall rader i en boks i brettet 
	 * 
	 */
	public int hentAntRadBoks() {
		return antradboks;
	}
	
	/** Henter antall kolonner i brettet 
	 * 
	 * @return antkol returnerer antall kolonner i brettet 
	 * 
	 */
	public int hentAntKol() {
		return antkol;
	}
	
	/** Henter antall kolonner i en boks i brettet 
	 * 
	 * @return antkolboks returnerer antall kolonner i en boks i brettet 
	 * 
	 */
	public int hentAntKolBoks() {
		return antkolboks;
	}
	
	/** Finner en losning paa brettet, og gjor dette ved aa begynne med den overste venstre ruten */
	public void fyllUtBrett(){
		brett[0][0].fyllUtRestenAvBrettet();
	}
	
	/** Henter beholderen som inneholder alle losningene for dette brettet 
	 * 
	 * @return beholder returnerer losningsbeholderen
	 * 
	 */
	public SudokuBeholder hentSudokuBeholder(){
		return beholder;	
	}
}