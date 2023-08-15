class Monitor {
	private int detMinste, detStorste;
	
	public boolean settMinste(int verdi) {
		detMinste = verdi;
		if (detMinste < detStorste) {
			return true;
		}
		else 
			return false;			
	}
	
	public boolean settStorste(int verdi) {
		detStorste = verdi;
		if (detMinste < detStorste) {
			return true;
		}
		else 
			return false;
	}
	
	public void settStartVerdiStorste(int verdi) {
		if (detStorste == 0) {
			detStorste = verdi;
		}
	}
	
	public void settStartVerdiMinste(int verdi) {
		if (detMinste == 0) {
			detMinste = verdi;
		}
	}
	
	public void skrivUtStorste() {
		System.out.println("Den største verdien er " + detStorste + " før avslutning.");
	}
	
	public void skrivUtMinste() {
		System.out.println("Den minste verdien er " + detMinste + " før avslutning.");
	}
	
}
