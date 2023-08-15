class HeapSort {
	private int[] a;
	
	public void heapSort(int[] a) {
		this.a = a;
		int n = a.length -1; 
		
		for (int k = 1; k <= n ; k++) 
			dyttOpp(k);
	
		bytt(0,n);
	
		for (int k = n-1; k > 0; k--) { 
			dyttNed(0,k);
			bytt(0,k);
		} 
	}
	
	public void dyttOpp(int i) {
		// Bladnoden på plass i er (muligens) feilplassert 
		// dytt den oppover mot rota til hele treet
		int j = (i-1)/2, temp = a[i]; 
	
		while (temp > a[j] && i > 0) {
			a[i] = a[j]; 
			i = j; 
			j = (i-1)/2;
		}
	
		a[i] = temp;
	}
	
	public void dyttNed(int i, int n) {
		// Rota er (muligens) feilplassert // Dytt gammel nedover
		// få ny større oppover
		int j = 2*i+1, temp = a[i];
		
		while (j <= n) { 
			if (j < n && a[j+1] > a[j]) 
				j++;
		
			if (a[j] > temp) {
				a[i] = a[j];
				i = j;
				j = j*2+1;
			}
			else 
				break;
		}
		
		a[i] = temp;
	}
	
	public void bytt(int k, int m) { 
		int temp = a[k];
		a[k] = a[m];
		a[m] = temp;
	}
	
}
