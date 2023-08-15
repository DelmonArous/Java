
class PaaVannet extends Hjerter {

	public PaaVannet(int id) {
		super(id);
		gruppe = "noe";
		settMaksfart(5);
		System.out.println(hentMaksfart());
	}

	public String hvorErJeg() {
		return "Der JA";
	}
	
}