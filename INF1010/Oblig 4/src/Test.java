import java.io.*;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws IOException {
		
		Scanner innFil = null;
		try{
			File f = new File("data.txt");
			innFil = new Scanner(f);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		int hashteller = 0, tellerpersoner = 0 , tellerlegemidler = 0;
		
		while(innFil.hasNextLine()){
			String linje = innFil.nextLine();
			String [] ord = linje.split(", ");
			
			if(linje.equals(""
					+ "")){
				continue;
			}
			
			if(ord[0].equals("ï»¿# Personer (nr") || ord[0].equals("# Legemidler (nr") || ord[0].equals("# Leger (navn")){
				hashteller++;
				continue;
			}
			
			if(hashteller == 1){
				tellerpersoner++;
			}
			if(hashteller == 2)
				tellerlegemidler++;
		}

		Tabell<Personer> personbeholder = new Tabell<Personer>(tellerpersoner);		
		Tabell<Legemidler> legemiddelbeholder = new Tabell<Legemidler>(tellerlegemidler);
		SortertEnkelListe<Leger> legeliste = new SortertEnkelListe<Leger>();			
		EnkelReseptListe yngstereseptbeholder = new YngsteForstReseptListe();
		
		try{
			File f = new File("data.txt");
			innFil = new Scanner(f);
		}catch(Exception e){
			System.out.println("Fant ikke filen");
		}
		
		
		hashteller = 0;
		while(innFil.hasNextLine()){
			String linje = innFil.nextLine();
			String [] ord = linje.split(", ");
			
			if(linje.equals(""
					+ "")){
				continue;
			}
			
			if(ord[0].equals("ï»¿# Personer (nr") || ord[0].equals("# Legemidler (nr") || ord[0].equals("# Leger (navn")
					|| ord[0].equals("# Resepter (nr") || ord[0].equals("# Slutt")){
				hashteller++;
				continue;
			}
			
			if(hashteller == 1){
				int nr = Integer.parseInt(ord[0]);
				personbeholder.leggInn(new Personer(nr,ord[1],ord[2]), nr);
			}
			else if(hashteller == 2){
				int nr = Integer.parseInt(ord[0]);
				if(ord[3].equals("a")){
					if(ord[2].equals("pille")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new PilleTypeA(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
					if(ord[2].equals("injeksjon")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new InjeksjonTypeA(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
					if(ord[2].equals("liniment")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new LinimentTypeA(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
				}
				if(ord[3].equals("b")){
					if(ord[2].equals("pille")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new PilleTypeB(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
					if(ord[2].equals("injeksjon")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new InjeksjonTypeB(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
					if(ord[2].equals("liniment")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						int styrke = Integer.parseInt(ord[6]);
						legemiddelbeholder.leggInn(new LinimentTypeB(nr,ord[1],ord[2],ord[3],pris,mengde,styrke), nr);
					}
				}
				if(ord[3].equals("c")){
					if(ord[2].equals("pille")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						legemiddelbeholder.leggInn(new PilleTypeC(nr,ord[1],ord[2],ord[3],pris,mengde), nr);
					}
					if(ord[2].equals("injeksjon")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						legemiddelbeholder.leggInn(new InjeksjonTypeC(nr,ord[1],ord[2],ord[3],pris,mengde), nr);
					}
					if(ord[2].equals("liniment")){
						int pris = Integer.parseInt(ord[4]);
						int mengde = Integer.parseInt(ord[5]);
						legemiddelbeholder.leggInn(new LinimentTypeC(nr,ord[1],ord[2],ord[3],pris,mengde), nr);
					}
				}
			}
			else if(hashteller == 3){
				int spesnr = Integer.parseInt(ord[1]);
				int avtalenr = Integer.parseInt(ord[2]);
				legeliste.leggInnSortert(new Leger(ord[0],spesnr,avtalenr));
			}
			
			else if(hashteller == 4){
				int nr = Integer.parseInt(ord[0]);
				int persnr = Integer.parseInt(ord[2]);
				int legemiddelnr = Integer.parseInt(ord[4]);
				int reit = Integer.parseInt(ord[5]);
				yngstereseptbeholder.leggInn(new Resepter(nr, ord[1],persnr,ord[3],legemiddelnr,reit));
			}
		}
			
/*		
		Personer person1 = new Personer(0, "Trude Lutt", "k");
		Personer person2 = new Personer(1, "Finn Lutt", "m");
		Personer person3 = new Personer(2, "Bjoerg Bjoernson", "k");
		Personer person4 = new Personer(3, "Hasse Lasse Loff", "m");
		Personer person5 = new Personer(4, "Enpersonmedetveldiglangtnavnsomtilogmedinneholderdenorskebokstaveneæøå", "k");

		Legemidler legemiddel1 = new PilleTypeA(0, "Paralgin Forte", "pille", "a", 150, 8, 10);
		Legemidler legemiddel2 = new InjeksjonTypeC(1, "Placebo", "injeksjon", "c", 50, 10);
		Legemidler legemiddel3 = new LinimentTypeB(2, "Zalo", "liniment", "b", 75, 100, 4);

		Leger lege1 = new Leger("Tore Trompet", 1, 13);
		Leger lege2 = new Leger("Jens", 0, 0);
		Leger lege3 = new Leger("Alf-Aagir Blaamann", 1, 0);
		Leger lege4 = new Leger("Katrine-Line Persson", 0, 110);
		Leger lege5 = new Leger("Lars Lege", 1, 13);

		Resepter resept1 = new Resepter(0, "h", 0, "Tore Trompet", 0, 10);
		Resepter resept2 = new Resepter(1, "b", 2, "Alf-Aagir Blaamann", 2, 1);
		Resepter resept3 = new Resepter(2, "b", 2, "Alf-Aagir Blaamann", 1, 10);
		
		personbeholder.leggInn(person1, 0);
		personbeholder.leggInn(person2, 1);
		personbeholder.leggInn(person3, 2);
		personbeholder.leggInn(person4, 3);
		personbeholder.leggInn(person5, 4);
		
		legemiddelbeholder.leggInn(legemiddel1, 0);
		legemiddelbeholder.leggInn(legemiddel2, 1);
		legemiddelbeholder.leggInn(legemiddel3, 2);
		
		legeliste.leggInnSortert(lege1);
		legeliste.leggInnSortert(lege3);
		legeliste.leggInnSortert(lege5);
		legeliste.leggInnSortert(lege2);
		legeliste.leggInnSortert(lege4);
		
		yngstereseptbeholder.leggInn(resept1);
		yngstereseptbeholder.leggInn(resept2);
		yngstereseptbeholder.leggInn(resept3);
*/
		
		for(Resepter r: yngstereseptbeholder.itererGjennomAlle()){
			boolean fantPerson = false, fantLege= false, fantLegemiddel = false;
			for(Personer pers: personbeholder.itererGjennomAlle()){
				if(r.hentPersonNummer() == pers.hentIDnummer()){
					pers.leggInnResept(r);
					fantPerson = true;
				}
			}
			for(Leger lege: legeliste.itererGjennomAlle()){
				if(lege.hentNavn().equals(r.hentLegeNavn())){
					lege.leggInnResept(r);
					r.settInnLege(lege);
					fantLege = true;
				}
			}
			for(Legemidler lm: legemiddelbeholder.itererGjennomAlle()){
				if(r.hentLegemiddelNummer() == lm.hentIDnummer()){
					r.settInnLegemiddel(lm);
					fantLegemiddel = true;
				}
			}
			
			if(!fantPerson){
				System.out.println("Resepten med ID "+ r.hentIDnummer()+ " er skrevet ut til en "
						+ "person som vi ikke fant med pasientnummer: "+ r.hentPersonNummer());
			}
			if(!fantLege){
				System.out.println("Resepten med ID "+ r.hentIDnummer()+ " er skrevet ut av en "
						+ "lege vi ikke fant som heter"+ r.hentLegeNavn());
			}
			if(!fantLegemiddel){
				System.out.println("Resepten med ID "+ r.hentIDnummer()+ " så fant vi ikke legemiddelet "
						+ "med legemiddelnr: "+ r.hentLegemiddelNummer());
			}
		}
		
		
		int teller;
		
		for (Personer p: personbeholder.itererGjennomAlle()) {
			teller = 0;
			for (Resepter r: p.hentReseptbeholder().itererGjennomAlle()) {
				if (r.hentFarge().equals("b") && r.gyldigResept()) {
					teller++;
					System.out.println(p.hentNavn() + " har " + r.hentReit() + " reit igjen på " + r.hentLegemiddel().hentLegemiddelNavn());
				}
			}
			System.out.println(p.hentNavn() + " har " + teller + " antall gyldige blå resepter");
		}
		
		System.out.println();
		System.out.println("==================================");
		System.out.println();
		
		for (Leger l: legeliste.itererGjennomAlle()) {
			teller = 0;
			if (l.hentAvtalenummer() != 0) {
				System.out.println("Lege " + l.hentNavn()  + " har avtalenummer " + l.hentAvtalenummer());
				for (Resepter r: l.hentReseptbeholder().itererGjennomAlle()) {
					if(r.hentLegemiddel().hentType().equals("a")){
						teller++;
					}
				}
				System.out.println("Lege " + l.hentNavn() + " har skrevet ut " + teller + " antall resepter på narkotiske legemidler");
			}
		}
		
		System.out.println();
		System.out.println("==================================");
		System.out.println();
		
		
		int totalkvinner = 0, totalmenn = 0, total;
		for (Personer p: personbeholder.itererGjennomAlle()) {
			teller = 0;
			System.out.println(p.hentNavn() + " har:");
			for (Resepter r: p.hentReseptbeholder().itererGjennomAlle()) {
				if (r.gyldigResept() && r.hentLegemiddel().hentType().equals("b")){
					teller++;
					if(p.hentKjønn().equals("k")){
						totalkvinner++;
					}
					else{totalmenn++;}
				}
			}
			System.out.println(teller + " antall gyldige resepter som er skrevet ut på vanedannende legemidler");
		}
		
		total = totalkvinner+totalmenn;
		System.out.println("Det er totalt " + total + " gyldige resepter på vanedannende legemidler som er skrevet ut:");
		System.out.println( totalkvinner + " resepter er til kvinner" );
		System.out.println( totalmenn + " resepter er til menn");
		
	}
}
