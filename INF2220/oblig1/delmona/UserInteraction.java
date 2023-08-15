import java.util.Scanner;

public class UserInteraction {
	public static void main(String[] args) {
		// Create a new BST-object
		BinSearchTree<String> bintree = new BinSearchTree<String>();
		
		// Read in the words from the given .txt-file and fill them into the BST-object
		FileReader reader = new FileReader(args[0]); // specify the .txt-file as a command-line argument
		reader.readIntoBinTree(bintree);
		
		// Removed the the node with key "familie" from the tree, then insert it again
		bintree.remove("familie");
		bintree.insert("familie");
		
		System.out.println("----------------------------------------------");
		
		System.out.println("Final word of the dictionary: " + bintree.findMax());
		System.out.println("First word of the dictionary: " + bintree.findMin());
		System.out.println("The depth of the tree: " + bintree.treeDepth());
		System.out.println("The average depth of all the nodes: " + bintree.aveDepth());
		
		int[] depths = bintree.depthArray();
		for (int i = 0; i < depths.length; i++)
			System.out.println("Depth " + i + ": " + depths[i] + " node(s)");
		
		System.out.println("----------------------------------------------");
	
		// Spell-checking loop
		String word = new String();
		Scanner scan = new Scanner(System.in);
		int positivelookup;
		long starttime, stoptime;
		
		while(true) {
			System.out.print("Enter a word for lookup (type q to quit): ");
			
			word = scan.next().toLowerCase();
			
			if (word.equals("q"))
				System.exit(0);
			
			if (bintree.contains(word)) {
				System.out.println("\"" + word + "\" is found in the dictionary and spelled correctly.");
				System.out.println(" ");
			} else {
				positivelookup = 0;
				System.out.println("\"" + word + "\" is not found in the dictionary.");
				System.out.println("Similar words to \"" + word + "\" is generated: ");

				SimilarWords similar = new SimilarWords();
				boolean flag = false;
				
				starttime = System.currentTimeMillis();
				for (String similarword: similar.similarOne(word)) {
					if (bintree.contains(similarword)) {
						positivelookup++;
						System.out.println("--> " + similarword);
						flag = true;
					}
				}
				for (String similarword: similar.similarTwo(word)) {
					if (bintree.contains(similarword)) {
						positivelookup++;
						System.out.println("--> " + similarword);
						flag = true;
					}
				}
				for (String similarword: similar.similarThree(word)) {
					if (bintree.contains(similarword)) {
						positivelookup++;
						System.out.println("--> " + similarword);
						flag = true;
					}
				}
				for (String similarword: similar.similarFour(word)) {
					if (bintree.contains(similarword)) {
						positivelookup++;
						System.out.println("--> " + similarword);
						flag = true;
					}
				}
				stoptime = System.currentTimeMillis() - starttime;
				
				if (!flag)
					System.out.println("No similar words found in the dictionary.");
				else {
					System.out.println("The number of lookups that gave a positive answer: " + positivelookup);
					System.out.println("Time used to generate and look for similar words: " + stoptime + " ms");
				}
				System.out.println(" ");
	
			}
		}	
	}
}
