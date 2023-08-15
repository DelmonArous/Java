class LenkeListe<T extends Comparable<T>> {
	private Node forste, siste;
	private int antall, antminste;
	private T minste;
	
	private class Node {
		Node neste;
		T objektet;
		
		public Node(T t) {
			objektet = t;
		}
		
		public void finnLaveste() {
			if (minste == null) {
				minste = objektet;
				antminste = 1;
			}
			else {
				if (objektet.compareTo(minste) == 0) {
					antminste++;
				}
				else if (objektet.compareTo(minste) < 0) {
					minste = objektet;
					antminste = 1;
				}	
			}
			
			if (neste != null) {
				neste.finnLaveste();
			}
			else {
				System.out.println("Minste objekt er " + minste + " og forekommer " + antminste + " ganger.");
			}
		}
		
		public void settInn(T t) {
			Node n = new Node(t);
			
			if (neste == null) {
				neste = n;
				return;
			}
			else {
				neste.settInn(t);
			}
		}
		
	}
	
	public void finnLavesteElement() {
		forste.finnLaveste();
	}
	
	public void settInnRekursivt(T t) {
		Node n = new Node(t);
		
		if (forste == null) {
			forste = n;
			return;
		}
		else {
			forste.settInn(t);
		}
	}
	
	public void settInnBak(T t) {
		Node n = new Node(t);
		if (siste != null) {
		    siste.neste = n;
		}
		else {
		    forste = n;
		}
		siste = n;
		antall++;
    }

    public T taUtForan() {
		Node n = forste;
		if (n != null) {
		    antall--;
		    forste = n.neste;
		    return n.objektet;
		}
		else {return null;}
    }
}
