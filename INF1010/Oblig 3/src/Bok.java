class Bok implements Gave {
	private String kat, id;
	 
	Bok(String i) {
		kat = "bok";
		id = i;
	}
	 
	public String kategori() {
		 return kat;
	}

	public String gaveId() {
		return id;
	}
}