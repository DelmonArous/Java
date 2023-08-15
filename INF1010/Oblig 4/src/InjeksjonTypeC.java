/*
 * Subklasse av LegemiddelTypeC som beskriver vanedannende legemidler som
 * har injeksjon som form. Dette ved å implementere grensesnittet Injeksjon.
 */

public class InjeksjonTypeC extends LegemiddelTypeC implements Injeksjon{
	private String form;
	
	public InjeksjonTypeC(int iNr, String n, String f, String t, double p, int m){
		super(iNr, n, t, p, m); // kaller på konstruktøren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}