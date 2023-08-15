/*
 * Subklasse av LegemiddelTypeC som beskriver narkotiske legemidler som
 * har piller som form. Dette ved � implementere grensesnittet Pille.
 */

public class PilleTypeC extends LegemiddelTypeC implements Pille {
	private String form;
	
	public PilleTypeC(int iNr, String n, String f, String t, double p, int m){
		super(iNr, n, t, p, m); // kaller p� konstrukt�ren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}
