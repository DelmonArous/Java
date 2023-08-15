/*
 * Subklasse av superklassen Legemidler. Objekter av denne subklassen 
 * er legemidler av typen "a".
 */

public class LegemiddelTypeA extends Legemidler{
	protected String type;
	protected int styrke; // de narkotiske legemidlene har en ekstra egenskap, styrke
	
	public LegemiddelTypeA(int iNr, String n, String t, double p, int m, int s){
		super(iNr,n,p,m); // kaller på konstruktøren i superklassen Legemidler
		type  = t;
		styrke = s;
	}
	
	public String hentType(){
		return type;
	}
	
	public int hentStyrke() {
		return styrke;
	}
}
