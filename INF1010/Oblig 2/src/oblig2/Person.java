package oblig2;

class Person {
	private String navn;
	private Person [] kjenner; // venner blir da alle personer som  pekes på av kjenner
	private Person [] likerikke; // unntatt personen(e) som pekes på av likerikke
	private Bok [] boksamling;
	private Plate [] platesamling;
	private Person forelsketi;	
	private String interessertiartist; // merker favorittartist
	private int interessertibokeldreenn; // merker favorittbøker
	private boolean tomplatesamling = true; // boolske variabler som definerer om
	private boolean tomboksamling = true; // samlingene er tomme eller ikke
	private int antallp = 0; // antall plater i platesamling
	private int antallb = 0; // antall bøker i boksamling
	
	public Person(String n, int lengde) {
		navn = n;
		kjenner = new Person[lengde];
		likerikke = new Person[lengde];
	}
	
	public Person(String n) {
		navn = n;
	}
	
	public String hentNavn() {
		return navn;
	}
	
	public void samlerAv(String smlp, int ant) { 
		if (smlp.equals("bøker")) { // opprett ny array for å samle på bøker
			boksamling = new Bok[ant]; // boksamling kan inneholde ant antall Bok objekter
			tomboksamling = false; // personobjektet har en boksamling
		}
		else if (smlp.equals("plater")) { // opprett ny array for å samle på plater
			platesamling = new Plate[ant]; // platesamling kan inneholde ant antall Plate objekter
			tomplatesamling = false; // personobjektet har en platesamling
		}
	}
	
	public void megetInteressertI(String artist) {
		if (tomplatesamling == false) { // dersom personobjektet har en platesamling
			interessertiartist = artist; // lagrer vi favorittartisten ved å kalle på metoden
		}
	}
	
	public void megetInteressertI(int eldreEnn) { // Se kommentar i foregående metode
		if (tomboksamling == false) {
			interessertibokeldreenn = eldreEnn;
		}
	}

	public Bok vilDuHaGaven(Bok b) {
		if (tomboksamling == true) { // har ingen boksamling
			return b;
		}
		else { // har boksamling
			for (int i = 0; i < boksamling.length; i++) {
				if (b == boksamling[i]) { // sjekk om gaven finnes i samlingen
					return b; // beholder ikke dersom sann
				}
			}
			if (antallb < boksamling.length/2.) { 
				for (int i = 0; i < boksamling.length; i++) { // loop gjennom boksamling
					if (boksamling[i] == null) { // for å finne et "tomt" element i boksamlings-arrayen
						boksamling[i] = b; // beholder gaven i dette elementet
						antallb++; // øker antall bøker i samlingen med +1
						return null; // beholder gaven
					}
				}
			}
			// hvis boksamling.length <= antallb < boksamling.length OG personobjektet er interessert i bok b  
			else if (antallb < boksamling.length && b.hentAarstall() <= interessertibokeldreenn) {
				for (int i = 0; i < boksamling.length; i++) {
					if (boksamling[i] == null) {
						boksamling[i] = b;
						antallb++;
						return null;
					}
				}
			} 
			// hvis boksamlingen er full OG personobjektet er interesserti bok b
			else if (antallb == boksamling.length && b.hentAarstall() <= interessertibokeldreenn) {
				for (int i = 0; i < boksamling.length; i++) { // loop gjennom boksamlingen for å finne et bokobjekt
					if (boksamling[i].hentAarstall() >= interessertibokeldreenn) { // som personobjektet ikke er interessert i
						Bok temp = boksamling[i]; // og bytter dette
						boksamling[i] = b; // med et som ikke er så fullt interessant for personobjektet
						return temp; // returner bokobjektet som ikke er av interesse for personobjektet 
					}
					// Det er verdt å merke at personobjektet kan ha boksamlingen full av favoritter + få 
					// en gave som faller under sin interesse
				}
			}
			else { // For mange bøker i samling siden antallb > boksamling.length
				return b; // beholder ikke gaven
			}
			
		}
		return b;
	}

	public Plate vilDuHaGaven(Plate p) { // Se kommentarer i forrige metode
		if (tomplatesamling == true) {
			return p;
		}
		else {
			for (int i = 0; i < platesamling.length; i++) {
				if (p == platesamling[i]) {
					return p;
				}
			}
			if (antallp < platesamling.length/2.) { 
				for (int i = 0; i < platesamling.length; i++) {
					if (platesamling[i] == null) {
						platesamling[i] = p;
						antallp++;
						return null;
					}
				}
			} 
			else if (antallp < platesamling.length && p.hentArtist().equals(interessertiartist)) {
				for (int i = 0; i < platesamling.length; i++) {
					if (platesamling[i] == null) {
						platesamling[i] = p;
						antallp++;
						return null;
					}
				}
			} 
			else if (antallp == platesamling.length && p.hentArtist().equals(interessertiartist)) {
				for (int i = 0; i < platesamling.length; i++) {
					if (platesamling[i].hentArtist().equals(interessertiartist) == false) {
						Plate temp = platesamling[i];
						platesamling[i] = p;
						return temp;
					}
				}
			}
			else {
				return p;
			}
			
		}
		return p;
	}
	
	public boolean settInnPlate(Plate [] p) {
		if (antallp == platesamling.length) {
			return false; // ikke lenger plass i platesamling
		}
		for (Plate pl: p) {
			platesamling[antallp] = pl; // legg inn plate p i platesamling
			antallp++;
		}
		return true;
	}
	
	public boolean settInnBok(Bok [] b) { // Se kommentar i foregående metode
		if (antallb == boksamling.length) {
			return false;
		}
		for (Bok bo: b) {
			boksamling[antallb] = bo;
			antallb++;
		}
		return true;
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
	    			kjenner[i] = p;
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
		if (p.hentNavn() != navn) {
			forelsketi = p;
		}
	} 
	
	public void blirUvennMed(Person p) {
		if (erKjentMed(p)) {
			for (int i = 0; i < likerikke.length; i++) {
				if (p.hentNavn().equals(navn) && likerikke[i] == null) {
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
		if(likerikke[0] != null){
			System.out.print(navn + " liker ikke ");
			skrivUtLikerIkke();
		}
	}

}