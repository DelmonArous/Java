public class Test {
	public static void main(String[] args) {
		
		LenkeListe<TallKlasse> liste = new LenkeListe<TallKlasse>();
		
		TallKlasse t1 = new TallKlasse(1);
		TallKlasse t2 = new TallKlasse(5);
		TallKlasse t3 = new TallKlasse(2);

		liste.settInnRekursivt(t1);
		liste.settInnRekursivt(t2);
		liste.settInnRekursivt(t3);

		System.out.println(liste.finnLavesteElement().hentVerdi());
		
		
		
	}
}
