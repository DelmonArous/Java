package oblig2;

public class Test {
	public static void main(String [] args) {
		// Deklarering av Person-objekter
		Person Kaia = new Person("Kaia");
		Person Josef = new Person("Josef");
		Person Ask = new Person("Ask");
		Person Dana = new Person("Dana");
		Person Tom = new Person("Tom");
		Person Jan = new Person("Jan");
		Person Tina = new Person("Tina");
		
		// Deklarering av Bok-objekter
		Bok bok1 = new Bok("Forfatter 1", "Tittel 1", 1842);
		Bok bok2 = new Bok("Forfatter 2", "Tittel 2", 1956);
		Bok bok3 = new Bok("Forfatter 3", "Tittel 3", 1921);
		Bok bok4 = new Bok("Forfatter 4", "Tittel 4", 1889);
		Bok bok5 = new Bok("Forfatter 5", "Tittel 5", 1967);
		Bok bok6 = new Bok("Forfatter 6", "Tittel 6", 1992);
		Bok bok7 = new Bok("Forfatter 7", "Tittel 7", 1812);

		// Deklarering av Plate-objekter
		Plate plate1 = new Plate("Silya Nymoen", "Singel 1", 1);
		Plate plate2 = new Plate("Bob Dylan", "Tempest", 10);
		Plate plate3 = new Plate("Bob Dylan", "Desire", 9);
		Plate plate4 = new Plate("Queen", "A Night at the Opera", 12);
		Plate plate5 = new Plate("Queen", "News of the World", 11);
		Plate plate6 = new Plate("Artist 6", "Tittel 6", 13);
		Plate plate7 = new Plate("Artist 7", "Tittel 7", 8);
		
		// Gir hvert Person-objekt riktig innhold av Bok/Plate-objekter
		Kaia.samlerAv("bøker", 5);
		Kaia.samlerAv("plater", 5);
		Bok [] boklistekaia = new Bok[]{bok2, bok5, bok6};
		Plate [] platelistekaia = new Plate[]{plate4, plate5, plate7};
		Kaia.settInnBok(boklistekaia);
		Kaia.settInnPlate(platelistekaia);
		Kaia.megetInteressertI("Queen");
		Kaia.megetInteressertI(1946);
		
		Josef.samlerAv("bøker", 5);
		Josef.samlerAv("plater", 5);
		Bok [] boklistejosef = new Bok[]{bok1, bok6};
		Plate [] platelistejosef = new Plate[]{plate1, plate4, plate6};
		Josef.settInnBok(boklistejosef);
		Josef.settInnPlate(platelistejosef);
		Josef.megetInteressertI("Silya Nymoen");
		
		Ask.samlerAv("bøker", 5);
		Ask.samlerAv("plater", 5);
		Bok [] boklisteask = new Bok[]{bok1, bok2, bok7};
		Plate [] platelisteask = new Plate[]{plate2, plate3};	
		Josef.settInnBok(boklisteask);
		Josef.settInnPlate(platelisteask);
		Ask.megetInteressertI(1900);
		
		Dana.samlerAv("plater", 5);
		Plate [] platelistedana = new Plate[]{plate2, plate6, plate7};
		Dana.settInnPlate(platelistedana);
		Dana.megetInteressertI("Bob Dylan");
		
		Tom.samlerAv("plater", 5);
		Plate [] platelistetom = new Plate[]{plate1, plate3, plate5};
		Tom.settInnPlate(platelistetom);
		
		Jan.samlerAv("bøker", 5);
		Bok [] boklistejan = new Bok[]{bok2, bok3, bok4};
		Jan.settInnBok(boklistejan);
		
		//
		Person [] allepersoner = new Person[]{Kaia, Josef, Ask, Dana, Tom, Jan, Tina};
		Bok [] alleboker = new Bok[]{bok1, bok2, bok3, bok4, bok5, bok6, bok7};
		Plate [] alleplater = new Plate[]{plate1, plate2, plate3, plate4, plate5, plate6, plate7};
		int i = 0;
		for (Bok b: alleboker) {
			boolean daarliggave = true;
			for (Person p: allepersoner) {
				if (p.vilDuHaGaven(b) == null || p.vilDuHaGaven(b) != b) { // hvis gaven beholdes ELLER er brukt
					daarliggave = false;
					break;
				}
			}
			if (daarliggave) {
				i++;
				System.out.println("Boken " + b.hentTittel() + " av " + b.hentForfatter() + " er en dårlig gave.\n");
				// slett/kast bok b
			}
		}
		i = 0;
		for (Plate pl: alleplater) {
			boolean daarliggave = true;
			for (Person p: allepersoner) {
				if (p.vilDuHaGaven(pl) == null || p.vilDuHaGaven(pl) != pl) {
					daarliggave = false;
					break;
				}
			}
			if (daarliggave) {
				i++;
				System.out.println("Platen " + pl.hentTittel() + " av " + pl.hentArtist() + " er en dårlig gave.\n");
				// slett/kast plate pl
			}
		}
	}
}