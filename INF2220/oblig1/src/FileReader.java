import java.util.*;
import java.io.*;

class FileReader {

	private Scanner scan;
	
	public FileReader(String filename) {
		
		try {
			scan = new Scanner(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readIntoBinTree(BinSearchTree<String> bintree) {
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++)
				bintree.insert(words[i]);
		}
		scan.close();
	}
	
}
