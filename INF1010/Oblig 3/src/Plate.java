class Plate implements Gave {
	private String kat, id;
	
	Plate(String i) {
		kat = "cd";
    		id = i;
    }
    
    public String kategori() {
    	return kat;
    }
    
    public String gaveId() {
    	return id;
    }
}