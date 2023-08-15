public class BruteForceSearch {
	private char[] text, pattern;
	private int n, m;
	
	public BruteForceSearch(String text, String pattern) {
		this.text = text.toCharArray();
		this.pattern = pattern.toCharArray();
		n = text.length();
		m = pattern.length();
	}
	
	public int runSearch() {
		int j;
		
		for (int i = 0; i <= n-m; i++) {
			j = 0;
			while (j < m && text[i+j] == pattern[j])
				j++;
			if (j == m)
				return i; // index in text where pattern starts
		}
		
		return -1; // pattern not found
	}
	
}