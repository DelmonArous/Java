
class Unntak extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Unntak(String melding) {
		super(melding);
		//System.out.println(melding);
		System.exit(1);
	}
	
}
