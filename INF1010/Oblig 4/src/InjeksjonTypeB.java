/*
 * Subklasse av LegemiddelTypeB som beskriver vanedannende legemidler som
 * har injeksjon som form. Dette ved å implementere grensesnittet Injeksjon.
 */

public class InjeksjonTypeB extends LegemiddelTypeB implements Injeksjon{
	private String form;
	
	public InjeksjonTypeB(int iNr, String n, String f, String t, double p, int m, int s){
		super(iNr, n, t, p, m, s); // kaller på konstruktøren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}