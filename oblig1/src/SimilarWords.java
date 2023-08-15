class SimilarWords {

	/** A word identical to X, except that two letters next to each other have been switched */
	public String[] similarOne(String word){
		
		char[] word_array = word.toCharArray();
		char[] tmp;	
		
		String[] words = new String[word_array.length-1];
		
		for (int i = 0; i < word_array.length - 1; i++) {
			tmp = word_array.clone();
			words[i] = swap(i, i+1, tmp);
		}
		
		return words;
	}

	public String swap(int a, int b, char[] word){
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;
		
		return new String(word);
	}

	/** A word identical to X, except one letter has been replaced with another */
	public String[] similarTwo(String word) {
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzæøå".toCharArray();
		char[] word_array = word.toCharArray();
		char[] tmp;
		
		String[] words = new String[(alphabet.length-1)*word_array.length];
		int k = 0;
		
		for (int i = 0; i < word_array.length; i++) {
			for (int j = 0; j < alphabet.length; j++){
				if (word_array[i] != alphabet[j]) {
					tmp = word_array.clone();
					tmp[i] = alphabet[j];
					words[k++] = new String(tmp);
				}
			}
		}
		
		return words;		
	}

	/** A word identical to X, except one letter has been removed */
	public String[] similarThree(String word) {
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzæøå".toCharArray();
		char[] word_array = word.toCharArray();
		char[] tmp = new char[word_array.length+1];
		
		String[] words = new String[alphabet.length*(word_array.length+1)];
		int k = 0;
		
		for (int i = 0; i < word_array.length+1; i++) {
			for (int j = 0; j < word_array.length; j++){
				if (i <= j)
					tmp[j+1] = word_array[j];
				else
					tmp[j] = word_array[j];
			}
			for (char c: alphabet) {
				tmp[i] = c;
				words[k++] = new String(tmp);
			}
		}
		
		return words;		
	}
	
	/** A word identical to X, except one letter has been added in front, 
	 * or at the end, or somewhere in between */
	public String[] similarFour(String word) {
		
		char[] word_array = word.toCharArray();
		char[] tmp = new char[word_array.length-1];
		
		String[] words = new String[word_array.length];
		
		for (int i = 0; i < word_array.length; i++) {
			for (int j = 0; j < word_array.length-1; j++) {
				if (i < j)
					tmp[j] = word_array[j+1];
				else
					tmp[j] = word_array[j];
			}
			words[i] = new String(tmp);
		}
		
		return words;		
	}
	
	
}
