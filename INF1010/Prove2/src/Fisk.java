
class Fisk extends Dyr implements Rollemodell {
	private Object rollemodell;
	
	public Fisk(String navn, int alder) {
		super(navn, alder);
	}

	public void settRollemodell(Object o) {
		rollemodell = o;
	}

	public Object hentRollemodell() {
		return rollemodell;
	}

}
