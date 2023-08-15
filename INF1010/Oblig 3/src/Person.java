class Person {
	private String navn;
	private Person [] kjenner = new Person[100]; // venner blir da alle personer som pekes på av kjenner
	private Person [] likerikke = new Person[10]; // unntatt personen(e) som pekes på av likerikke
	private Gave [] mineGaver; // kan inneholde objekter av klasser som implementerer Gave-grensesnittet
	private int antallg = 0; // antall gaver i gavesamling
	private String interessertikategori; // dette markerer interessen for dette Person-objektet
	Person forelsketi; // peker til Person-objektet denne personen er forelsket i
	Person sammenmed; // peker til Person-objektet denne personen er sammen med
	Person neste; // peker til neste person
	
	public Person(String n) {
		navn = n;
	}
    
	public String hentNavn() {
		return navn;
	}
	
	public void samlerAv(String smlp, int ant) { 
		if (smlp.equals("bok") || smlp.equals("cd") || smlp.equals("vin")) { // opprett ny array for å motta bok-gaver
			interessertikategori = smlp;
			mineGaver = new Gave[ant]; // gavesamlingen kan inneholde ant antall Bok/Plate/Vin objekter
		}
		else {
			interessertikategori = smlp;
			mineGaver = new Gave[ant];
			System.out.println(this.navn + " har interessen " + "'" + smlp + "'" + " som ikke er gyldig.");
		}
	}
	
	public Gave gaveMottak(Gave g) {
		Gave test = this.vilDuHaGaven(g);
		if (test == null) {
			return null;
			}
		else {
			if (sammenmed != null) { // dersom dette personobjektet er sammen med noen
				test = sammenmed.vilDuHaGaven(g);
				if (test == null){ // forsøker å tilby gaven til den sammenmed-personobjektet
					return null;
				}
				if(forelsketi != null) {
					test = forelsketi.vilDuHaGaven(g);
					if (test == null) { // forsøker så å tilby gaven til forelsketi-personobjektet
						return null;
					}
				}
				for (int i = 0; i < kjenner.length; i++) {
					if(kjenner[i] != null){
						test = kjenner[i].vilDuHaGaven(g);
						if (test == null) { // forsøker til slutt å tilby gaven til en venn i kjennerlisten
							return null;
						}
					}	
				}
				return g;
			}
			else if (sammenmed == null && forelsketi != null) { // dersom dette Person-objektet er ikke sammen med noen 
				test = forelsketi.vilDuHaGaven(g); 				// OG er forelsket i noen
				if (test == null){
					return null;
				}
				for (int i = 0; i < kjenner.length; i++) {
					if(kjenner[i] != null){
						test = kjenner[i].vilDuHaGaven(g);
						if (test == null) {
							return null;
						}
					}	
				}
				return g;
			}
			else { // dersom dette Person-objektet er verken sammen med eller forelsket i noen
				for (int i = 0; i < kjenner.length; i++) {
					if(kjenner[i] != null){
						test = kjenner[i].vilDuHaGaven(g);
						if (test == null) {
							return null;
						}
					}	
				}
			}
			return g;
			}
		}
	
	public Gave vilDuHaGaven(Gave g) {
		// Hvis dette Person-objektet er interessert i Gave g som ligger innenfor en kategori
		// OG hvis personobjektet har plass i sin gavesamling, så setter vi inn gaven g i gavesamling
		if (g.kategori().equals(interessertikategori) && antallg != mineGaver.length) {
			settInnGave(g);
			return null;
		}
		return g;
	}
	
	public boolean settInnGave(Gave g) {
		if (antallg == mineGaver.length) {
			return false; // ikke lenger plass i gavesamling
		}
		mineGaver[antallg] = g; // legg Gave g til i mineGaver-arrayen
		antallg++;
		return true;
	}
	
	public void blirSammenMed(Person p) {
		if (p.hentNavn().equals(navn)) {
			// Feilmelding: kan ikke bli sammen med seg selv!
		}
		else if (erKjentMed(p)) {
			sammenmed = p; // dette personobjektet blir sammen med person p
			p.sammenmed = this; // og tilsvarende blir person p sammen med dette personobjektet
		}
	}
	
	public void blirKjentMed(Person p) {
		if (p.hentNavn().equals(navn)) {
			// Feilmelding: kan ikke bli kjent med seg selv!
	    } 
		else if (erKjentMed(p)) {
	        // Feilmelding: er allerede kjent med p! Kan ikke legge duplikater
	    } 
	    else {
	    	for (int i = 0; i < kjenner.length; i++) {
	    		if (kjenner[i] == null) {
	    			kjenner[i] = p; // legg Person p til i kjenner-arrayen
	    			break;
	    		}
	    	}
	    }
	}
	
	public boolean erKjentMed(Person p) {
		for (int i = 0; i < kjenner.length; i++) {
			if (kjenner[i] == p) {
				return true;
			}
		}
		return false;
	}

	public void blirForelsketI(Person p) {
		if (!p.hentNavn().equals(navn)) {
			for (Person pers: likerikke) {
				if (p == pers) {
					return;
					// Feilmelding: kan ikke være forelsket i en person hun ikke liker
				}
			}
			forelsketi = p;
		}
		else {
			// Feilmelding: kan ikke være forelsket i seg selv
		}
	} 
	
	public void blirUvennMed(Person p) {
		if (erKjentMed(p)) {
			for (int i = 0; i < likerikke.length; i++) {
				if (!p.hentNavn().equals(navn) && likerikke[i] == null) {
					likerikke[i] = p;
					break;
				}
			}	
		}
	}
	
	public boolean erVennMed(Person p) {
		for (int i = 0; i < kjenner.length; i++) {
			for (int j = 0; j < likerikke.length; j++) {
				if (p == kjenner[i] && p != likerikke[j] && likerikke[j] != null) {return true;}
			}
		}
		return false;	
	}
	
	public void blirVennMed(Person p) {
		for (int i = 0; i < likerikke.length; i++) {
			if (p == likerikke[i]) {
				likerikke[i] = null;
				blirKjentMed(p);
			}
		}
	}
	
	public void skrivUtVenner() {
		for (int i = 0; i < kjenner.length; i++) {
			if (erVennMed(kjenner[i])) {
				System.out.println(kjenner[i].hentNavn());
			}
		}
	}

	public Person hentBestevenn() {
		return kjenner[0];
	}
	
	public Person[] hentVenner() {
		Person [] venner = null;
		int teller = 0;
		for (int i = 0; i < kjenner.length; i++) {
			if (erVennMed(kjenner[i]) && kjenner[i] != null) {
				teller += 1;
			}
		}
		venner = new Person[teller];
		teller = 0;
		for (int i = 0; i < kjenner.length; i++) {
			if (erVennMed(kjenner[i]) && kjenner[i] != null) {
				venner[teller] = kjenner[i];
				teller += 1;
			}
		}
		return venner;
	}
	
	public int antVenner() {
		return hentVenner().length;
	}

	public void skrivUtKjenninger() {
		for (Person p: kjenner) {
			if (p != null) {
				System.out.print(p.hentNavn() + " " );
			}
		}
		System.out.println("");
	}

	public void skrivUtLikerIkke() {
		for (Person p: likerikke) {
			if (p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	}

	public void skrivUtAltOmMeg() {
		System.out.print(navn + " kjenner: ");
		skrivUtKjenninger();
		if (forelsketi != null) {
			System.out.println(navn + " er forelsket i " + forelsketi.hentNavn());
		}
		if (likerikke[0] != null){
			System.out.print(navn + " liker ikke ");
			skrivUtLikerIkke();
		}
		if (sammenmed != null) {
			System.out.println(navn + " er sammen med " + sammenmed.hentNavn());
		}
		if (antallg != 0) { // printer ut dersom dette Person-objektet har objekter i mineGaver-arrayen
			System.out.println(navn + " har disse gavene:");
			for (Gave g: mineGaver) {
				if (g != null) {
					System.out.println(g.kategori() + ": " + g.gaveId());
				}
			}
		}
	}

}
