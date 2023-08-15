/*
 * Subklasse av LegemiddelTypeB som beskriver narkotiske legemidler som
 * har piller som form. Dette ved � implementere grensesnittet Pille.
 */

public class PilleTypeB extends LegemiddelTypeB implements Pille {
	private String form;
	
	public PilleTypeB(int iNr, String n, String f, String t, double p, int m, int s){
		super(iNr, n, t, p, m, s); // kaller p� konstrukt�ren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}
