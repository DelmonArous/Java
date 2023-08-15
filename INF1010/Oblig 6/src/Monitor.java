class Monitor {
	private Node forste;
	private int antalltrader;
	private String [] tab;
	private String utfil;
	int makslengde, antall;
	
	public Monitor(int makslengde, String utfil) {
		this.makslengde = makslengde;
		this.utfil = utfil;
	}
	
	private class Node {
		Node neste;
		String [] obj;
		
		Node(String [] s) {obj = s;}
	}
	
	
	synchronized String [] Buffer(String [] subtabell) throws FeilMedAntOrd {
		String [] temp;
		
		if (tab == null) {
			tab = subtabell;
			if (tab.length == makslengde) { // den sorterte tabellen har lengden antOrd
				// Skriv den resulterende sorterte tabellen ut 
				new Skriver(makslengde, tab, utfil);
			}
			return null;
		}
		else {
			temp = tab;
			tab = null;
			return temp; // sender tilbake for fletting
		}
	}

	public synchronized void settInn(String [] tabell) {
		tab = tabell;
		
		if(tab.length == makslengde) {
			System.out.println("JEG SKRIVER");
			try {
				new Skriver(makslengde, tab, utfil);
			} catch (FeilMedAntOrd e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Setter inn");

		Node n = new Node(tabell);
		if (forste == null)
			forste = n;
		else {
			n.neste = forste;
			forste = n;
		}	
		
		//antalltrader++;
		antall++;
		notifyAll();
	}
	
	public synchronized String [] taUt() {	
		while(antall == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("JEG TAR UT");

		Node n = forste;
		if(n != null) {
			antall--;
			forste = n.neste;
			notifyAll();
			return n.obj;
		}

		notifyAll();
		return null;
	}
	
	
	public synchronized void ventPaaAlleFerdig(int ant) {
		while (ant != antalltrader) {
			try{wait();}
			catch(InterruptedException e) {e.printStackTrace();}
		}
	}
	
	
	
}
