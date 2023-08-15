class Unntak extends Exception {
	
	public Unntak(String melding) {
		super(melding);
		System.out.println(melding);
		System.exit(1);	
	}
}
