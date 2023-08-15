class Vin implements Gave {
	private String kat, id;
	
	Vin(String i) {
		kat = "vin";
		id = i;
	}
	
	public String kategori() {
		return kat;
	}
	
	public String gaveId() {
		return id;
	}
}
