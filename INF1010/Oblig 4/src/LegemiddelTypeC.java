/*
 * Subklasse av superklassen Legemidler. Objekter av denne subklassen 
 * er legemidler av typen "c".
 */

public class LegemiddelTypeC extends Legemidler{
	protected String type;
	
	public LegemiddelTypeC(int iNr, String n, String t, double p, int m){
		super(iNr,n,p,m); // kaller på konstruktøren i superklassen Legemidler
		type = t;
	}
	
	public String hentType(){
		return type;
	}
}