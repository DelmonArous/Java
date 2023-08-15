class HundDog implements UtNorsk, OutEnglish {
	private int alder;
	private String navn;
	
	HundDog(int alder, String navn) {
		this.alder = alder;
		this.navn = navn;
	}
	
	public void skriv() {
		System.out.println("Hunden " + navn + " er " + alder + " år gammel");
	}
	
	public void write() {
		System.out.println(navn + " the dog is " + alder + " years old");
	}
}
