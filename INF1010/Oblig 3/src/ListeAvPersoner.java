class ListeAvPersoner {
	private Person personliste, sistePerson;
	private int antall;
	
	public ListeAvPersoner() {
		Person lh = new Person("LISTEHODE!!");
		personliste = lh;
		sistePerson = lh;
		antall = 0;
	}
	
	/* 
	 * Invariante tilstandspåstander
	 * (skal gjelde før og etter alle metodekall):
	 * personliste peker på listehodet
	 * første person i lista er personliste.neste
	 * Hvis lista er tom er antall lik 0 og 
	 * personliste og sistePerson peker på
	 * listehodet og personliste.neste er null
	 */ 
	
	public void settInnFørst(Person nypers) {
		if (personIListe(nypers) == false) { // sjekker om personen nypers er allerede i lenkelisten
			nypers.neste = personliste.neste;
			personliste.neste = nypers;
			antall++;
			if (nypers.neste == null) { // nyperson er ny sist
				sistePerson = nypers;
			}
		}
		else {
			System.out.println("Personen " + nypers.hentNavn() + " er allerede i personlisten!");
		}
	}
	
	public void settInnSist(Person inn) {
		if (personIListe(inn) == false) { // sjekker om personen inn er allerede i lenkelisten
			sistePerson.neste = inn;
			sistePerson = inn;
			antall++;			
		}
		else {
			System.out.println("Personen " + inn.hentNavn() + " er allerede i personlisten!");
		}
	}
	
	public void settInnEtter(Person denne, Person inn) {
		if (personIListe(inn) == false && personIListe(denne) == true && denne != inn) {
			// dersom denne = inn: setter seg selv til å være neste, vil bli sirkulært dersom man legger til flere
			inn.neste = denne.neste;
			denne.neste = inn;
			antall++;
			if (inn.neste == null) { // inn er ny sist 
				sistePerson = inn;
			}
		}
		else {
			// Feilmelding: person inn kan allerede befinne seg i liste eller
			// person denne er ikke i personlisten eller person denne er lik person inn
		}
	}
	
	public Person finnPerson(String s) {
		Person p = personliste.neste;
		for (int i = antall; i > 0; i--) {
			if (p.hentNavn().equals(s)) {
				return p;
			}
			else {
				p = p.neste;
			}
		}
		return null;
	}
	
	public boolean personIListe(Person pers) {
		if (finnPerson(pers.hentNavn()) != null) { // søker om navnet til personen pers finnes i lenkelisten
			return true;
		}
		else {
			return false;
		}
	}
	
	public void skrivAlle() {
		Person p = personliste.neste;
		System.out.println("----------");
		for (int i = antall; i > 0; i--) {
			System.out.println(antall - i + 1 + ": ");
			p.skrivUtAltOmMeg();
			p = p.neste;
		}
		System.out.println("==========");
	}
	
}