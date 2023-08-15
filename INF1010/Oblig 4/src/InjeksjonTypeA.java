/*
 * Subklasse av LegemiddelTypeA som beskriver narkotiske legemidler som
 * har injeksjon som form. Dette ved � implementere grensesnittet Injeksjon.
 */

public class InjeksjonTypeA extends LegemiddelTypeA implements Injeksjon{
	private String form;
	
	public InjeksjonTypeA(int iNr, String n, String f, String t, double p, int m, int s){
		super(iNr, n, t, p, m, s); // kaller p� konstrukt�ren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}
