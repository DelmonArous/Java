import java.util.NoSuchElementException;

abstract class Hjerter {
	
	protected String gruppe;
	private int id, maksfart;
	
	public Hjerter(int id) {
		this.id = id;
	}

	public void settMaksfart(int a) {
		maksfart = a;
	}
	
	public int hentMaksfart() {
		return maksfart;
	}
	
	public void spillFil() {
		if (this instanceof Tog) {
			try {
				throw new NoSuchElementException("JALLA");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("Wut");
	}
	
	
}
