class RSiffer {
	private int r, rm1; // r-tallsystemet og r-1 hhv.
	private int [] sekv;
	private static long num = 0;
	private boolean [] brukt;

	public RSiffer(int r) {
		this.r = r; 
		rm1 = r-1;
		sekv = new int[r];
		brukt = new boolean[r];
	}
	
	public void finnSiffer(int i) {
		num++;
		
		for (int tall = 1; tall < r; tall++) {
			if (!brukt[tall]) {
				brukt[tall] = true;
				sekv[i] = tall;
				
				if (i == rm1 && erDelelig(rm1)) 
					skrivUt(); // losning
				else if (erDelelig(i)) // avskjearing
					finnSiffer(i+1);
				
				brukt[tall] = false;
			}
		}
		
	} // end finnSiffer

	private int finntall(int i) { 
		//int t = 0, rpot = 1;
		/*for (int k = i; k >= 1; k--) {
			t = t + sekv[k]*rpot;
			rpot *= r;
		}*/

		int t = 1;
		for (int k = 0; k < i; k++)
			t *= sekv[k];
		
		return t;
	}

	 private boolean erDelelig(int k) {
		 int t = finntall(k);
		 
		 if (t % k == 0) 
			 return true;
	     else
	    	 return false;
	}
	 
	 public void skrivUt() {
		 System.out.print("Sekvens " + num + " : ");
		 for (int i = 1; i < r; i++)
			 System.out.print (sekv[i]);
		 System.out.println  (" ");
	 }
	 
}