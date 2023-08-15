import java.io.*;
import java.util.*;

class BoyerMooreHorspool {
	private int CHAR_MAX = 256; // assume 1-byte characters
	private int[] bad_shift = new int[CHAR_MAX];
	private char[] needle, haystack;
	private List<Integer> matches = new ArrayList<Integer>(); // the index of each match as an array
	
	/*
	 * Reads in input-file and return the whole textfile as a String-object
	 */
	public String fileReader(String filename) {
		Scanner scan = null;
		String text = "";
		
		try {
			scan = new Scanner(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		while (scan.hasNext()) {
			text += scan.nextLine() + " ";
		}
		
		scan.close();
		
		return text;
	}
	
	/*
	 * Computes the bad character shift of the needle, and stores all the positions 
	 * of the matches between needle and haystack as an array.
	 */
	public Integer[] BadCharacterShift(String pattern, String text) {
		needle = pattern.toCharArray();
		haystack = text.toCharArray();
		int offset = 0, scan = 0, lastwildcard = 0;
		int last = needle.length - 1;
		int maxoffset = haystack.length - needle.length;
		
		if (needle.length > haystack.length) {
			System.out.println("Pattern is larger than the text!");
			return matches.toArray(new Integer[matches.size()]);
		} else if (needle.length == 0) {
			System.out.println("Pattern is empty!");
			return matches.toArray(new Integer[matches.size()]);
		}
			
		// Finding the index of second to last wildcard in needle.
		int counter = 0;
		for (char ch: needle) {
			if (ch == '_' && counter != last)
				lastwildcard = counter;
			counter++;
		}
		
		// Value given by: align the next wildcard in the needle to the haystack
		for (int i = 0; i < CHAR_MAX; i++)
			bad_shift[i] = last - lastwildcard;
		
		for (int i = 0; i < last; i++)
			bad_shift[needle[i]] = last - i;
		
		while (offset <= maxoffset) {
			for (scan = last; matchNeedleHaystack(scan, scan+offset); scan--) {
				if (scan == 0) { // match found!
					matches.add(offset);
					break;
				}
			}
			offset += bad_shift[haystack[offset + last]];
		}
		
		return matches.toArray(new Integer[matches.size()]);
	}
	
	/*
	 * Checks if character needle[i] matches character haystack[j].
	 * If character needle[i] is a wildcard, then it is always a match.
	 */
	public boolean matchNeedleHaystack(int i, int j) {
		
		if (needle[i] == '_')
			return true;
		
		return (needle[i] == haystack[j]);
	}
	
	public void printResults(Integer[] hits) {
		
		if (hits.length == 0) {
			System.out.println("Pattern not found in the text.");
			return;
		}
		
		for (int i = 0; i < hits.length; i++) {
			System.out.print("Index: " + hits[i] + ", pattern-match: ");
			for (int j = hits[i]; j < hits[i] + needle.length; j++)
				System.out.print(haystack[j]);
			System.out.print("\n");
		}
		
	}

}