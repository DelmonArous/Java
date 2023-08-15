class PersonNettverk {
	public PersonNettverk() {
		Personer p = new Personer(); // sett opp personnettverket
		ListeAvPersoner personliste = new ListeAvPersoner(); // initialiser lenkelisten personliste
		String [] navnliste = p.hentPersonnavn(); // dette er String arrayen som inneholder alle personnavnene, oblig3Navn
		
		GaveLager gavelager = new GaveLager(); // peker til et objekt med et lager av gaver 
		Gave nyGave = gavelager.hentGave(); // returner første gave fra gavelageret
		
		Person pers = p.hentPerson();
		while (pers != null) { // henter og setter inn personen pers inn i lenkelisten fram til det er ingen personer
			personliste.settInnSist(pers);
			pers = p.hentPerson();
		}
		
		while (nyGave != null) { // henter gaver og gir dem til alle personene
			for (int i = 0; i < navnliste.length; i++) {
				Gave x = personliste.finnPerson(navnliste[i]).gaveMottak(nyGave);
				if (x == null) {
					break; // dersom gaven blir gitt bort
				}
			}
			nyGave = gavelager.hentGave(); // henter ny gave fra gavelageret
		}
		personliste.skrivAlle(); // skriv ut info om alle personene lagret i lenkelisten
	}
}