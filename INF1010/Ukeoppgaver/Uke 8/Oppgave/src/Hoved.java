public class Hoved {
	public static void main(String[] args) {
		HundDog hunddog1 = new HundDog(1, "hund1");
		HundDog hunddog2 = new HundDog(2, "hund2");
		HundDog hunddog3 = new HundDog(1, "hund3");
		HundDog hunddog4 = new HundDog(3, "hund4");
		
		DobbeltLenketListe<HundDog> liste = new DobbeltLenketListe<HundDog>();
		
		liste.push(hunddog1);
		liste.push(hunddog2);
		liste.push(hunddog3);
		liste.push(hunddog4);

		for (HundDog hd: liste) {
			hd.skriv();
		}
	}

}
