
class Gullfisk extends Dyr implements Rollemodell {
	private String rollemodell;
	
	public Gullfisk(String navn, int alder) {
		super(navn, alder);
	}

	public void settRollemodell(String o) {
		rollemodell = o;
	}

	public String hentRollemodell() {
		return rollemodell;
	}

	
}
