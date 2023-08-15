
public class Hoved {

	public static void main(String[] args) {
		Fisk noe = new Fisk("Leif", 4);
		Fisk noe1 = new Fisk("Bob", 92194);
		
		noe.settRollemodell(noe1);
		
		Fisk rollemodell = (Fisk) noe.hentRollemodell();
		
		System.out.println(rollemodell.hentAlder());
		System.out.println(rollemodell.hentNavn());

	}

}
