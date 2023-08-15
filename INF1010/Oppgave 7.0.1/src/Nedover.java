class Nedover implements Runnable {
	private Monitor m;
	
	public Nedover() {
		m.settStartVerdiMinste(Integer.MAX_VALUE);
		/* hva med storste verdi */
	}
	
	public void run() {
		int temp = 10;
		while(m.settStorste(temp)) {
			temp--;
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}	
		}
		m.skrivUtStorste();
	}
	
}