
class Oppover implements Runnable {
	private Monitor m;
	
	public Oppover() {
		m.settStartVerdiStorste(Integer.MIN_VALUE);
		/* hva med storste verdi */
	}
	
	public void run() {
		int temp = 10;
		while(m.settMinste(temp)) {
			temp--;
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}	
		}
		m.skrivUtMinste();
	}
	
}