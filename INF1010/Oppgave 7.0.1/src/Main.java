public class Main {
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Nedover());
		Thread t2 = new Thread(new Oppover());
		
		t1.start();
		t2.start();

	}
}
