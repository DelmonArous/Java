class BinSokeTre {
	private BinNode rot;
	
	public BinNode settInn1(int x, BinNode t) {
		
		if (t == null) {
			return new BinNode(x, null, null);	
		}
		
		if (x < t.element)
			t.venstre = settInn1(x, t.venstre);
		else if (x > t.element)
			t.hoyre = settInn1(x, t.hoyre);
		else {
			
			if (t.hoyre == null) 
				t.hoyre = new BinNode(x, null, null);
			else {
				t.hoyre = settInn1(x, t.hoyre);
			}
			
		}		
		
		return t;
	}
	
	
	public BinNode settInn2(int x, BinNode t) {
		
		if (t == null) {
			return new BinNode(x, null, null);	
		}
		
		if (x < t.element)
			t.venstre = settInn2(x, t.venstre);
		else if (x > t.element)
			t.hoyre = settInn2(x, t.hoyre);
		else {
			
			if (t.hoyre.element != x) {
				t.hoyre = new BinNode(x, t.hoyre, null);
			} else {
				
				BinNode n = t.hoyre;
				
				while (n.venstre != null) {
					n = n.venstre;
				}
				
				n.venstre = new BinNode(x, null, null);
			}
		}
		
		rot = t;
		return t;
	}
	
	
	public void skrivUt(BinNode rot) {
		BinNode n = rot;
		
		if (n != null) {			 
			skrivUt(n.venstre);

			if (n.hoyre.element == n.element) {
				System.out.print("  " + n.element);
				n = n.hoyre;
				System.out.print("  " + n.element);
				
				if (n.venstre.element == n.element) {
					n = n.venstre;
					System.out.print("  " + n.element);
				}
				
				skrivUt(rot.hoyre.hoyre);
			}
			
			skrivUt(n.hoyre);	
		}
		
		return;
	}
	
	
	
}
