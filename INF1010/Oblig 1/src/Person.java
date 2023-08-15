class Person {
	private String navn;
	private Person [] kjenner;
	private Person [] likerikke;
	// venner blir da alle personer som  pekes på av kjenner
	// unntatt personen(e) som pekes på av likerikke
	private Person forelsketi;
	
	public Person(String n, int lengde) {
		navn = n;
		kjenner = new Person[lengde];
		likerikke = new Person[lengde];
	}
	
	public String hentNavn() {
		return navn;
	}
	
	public void blirKjentMed(Person p) {
		for (int i = 0; i < kjenner.length; i++) {
			if (p.hentNavn() != navn && kjenner[i] == null) {
				kjenner[i] = p;
				break;
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
		if (p.hentNavn() != navn) {
			forelsketi = p;
		}
	} 
	
	public void blirUvennMed(Person p) {
		for (int i = 0; i < likerikke.length; i++) {
			if (p.hentNavn() != navn && likerikke[i] == null) {
				likerikke[i] = p;
				break;
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
			if (p == likerikke[i]) {likerikke[i] = null;}
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
		if(likerikke[0] != null){
			System.out.print(navn + " liker ikke ");
			skrivUtLikerIkke();
		}
	}
}