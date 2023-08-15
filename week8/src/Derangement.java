class Derangement {
	private int[] p;
	private int n;
	private boolean[] used;
	
	public Derangement(int n) {
		this.n = n;
		p = new int[n];
		used = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			used[i] = false;
		}
	}
	
	public void Permute(int i) {
		
		for (int siff = 0; siff < n; siff++) {
			if (!used[siff] && i != siff) {
				used[siff] = true;
				p[i] = siff+1; // p = {1, 2, ..., n}
				if (i < n-1)
					Permute(i+1);
				else { // this gives a permutation of p = {1, 2, ..., n}, print it out
					for (int j = 0; j < n; j++)
						System.out.print(" " + p[j]);
					System.out.println(" ");
				}
				used[siff] = false;
			}
		}
		
	}
	
}