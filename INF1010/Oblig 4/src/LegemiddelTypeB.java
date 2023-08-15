/*
 * Subklasse av superklassen Legemidler. Objekter av denne subklassen 
 * er legemidler av typen "b".
 */

public class LegemiddelTypeB extends Legemidler{
	protected String type;
	protected int styrke; // de vanedannende legemidlene har en ekstra egenskap, styrke
	
	public LegemiddelTypeB(int iNr, String n, String t, double p, int m, int s){
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