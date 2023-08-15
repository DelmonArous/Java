public class TallKlasse implements Comparable<TallKlasse> {
	private int tall;
	
	public TallKlasse(int t) {
		tall = t;
	}
	
	public int hentVerdi() {
		return tall;
	}
	
	public int compareTo(TallKlasse t) {
		return this.tall - t.hentVerdi();
	}
	

}
