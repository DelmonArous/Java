class Test {
	public Test() {
		Person Jeg = new Person("Josef", 3);
		Person Ask = new Person("Ask", 3);
		Person Dana = new Person("Dana", 3);
		Person Tom = new Person("Tom", 3);
		
		Jeg.blirKjentMed(Dana);
		Jeg.blirKjentMed(Ask);
		Jeg.blirKjentMed(Tom);
		
		Ask.blirKjentMed(Dana);
		Ask.blirKjentMed(Jeg);
		Ask.blirKjentMed(Tom);
		Ask.blirForelsketI(Jeg);
		Ask.blirUvennMed(Dana);
		Ask.blirUvennMed(Tom);
		
		Dana.blirKjentMed(Ask);
		Dana.blirKjentMed(Tom);
		Dana.blirKjentMed(Jeg);
		Dana.blirForelsketI(Tom);
		Dana.blirUvennMed(Jeg);
		
		Tom.blirKjentMed(Jeg);
		Tom.blirKjentMed(Ask);
		Tom.blirKjentMed(Dana);
		Tom.blirUvennMed(Ask);
		Tom.blirUvennMed(Jeg);
		Tom.blirForelsketI(Dana);
		
		Jeg.skrivUtAltOmMeg();
		Ask.skrivUtAltOmMeg();
		Dana.skrivUtAltOmMeg();
		Tom.skrivUtAltOmMeg();
	}
}
