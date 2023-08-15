/* I denne obligen har jeg samarbeidet med Saurav Sharma (sauravsh) */

public class Oblig5 {	
	
	/*public void run() {
		new Leser(args);
	}*/
	
	public static void main(String[] args) {
		
		Thread t = new Thread();
		
		// Tråder må gjøres, i main og for GitterNxN
		
		if (args.length == 1 || args.length == 2) {
			new Leser(args); // t.start();
		}
		else {
			throw new IllegalArgumentException("Incorrect number of command line arguments: "
	                + args.length);
		}
		
	}

	
}
