public class Oblig3 {
	public static void main(String[] args) {
		BoyerMooreHorspool bmh = new BoyerMooreHorspool();
		
		// Read in command-line arguments
		if (args.length != 2) {
			System.out.println("InputError!");
			System.exit(0);
		}

		String pattern = args[0];
		String text = args[1];
		System.out.println("Text: " + text + "\n");
		System.out.println("Pattern: " + pattern);
		Integer[] matches = bmh.BadCharacterShift(pattern, text);
		bmh.printResults(matches);
		System.out.println("------------------------------------------");
		
		// Read in an input-file as haystack
		/*
		String text = bmh.fileReader(filename);
		Integer[] matches = bmh.BadCharacterShift(pattern, text);
		bmh.printResults(matches);
		*/	
	}
}