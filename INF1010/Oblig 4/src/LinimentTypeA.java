/*
 * Subklasse av LegemiddelTypeA som beskriver narkotiske legemidler som
 * har liniment som form. Dette ved å implementere grensesnittet Liniment.
 */

public class LinimentTypeA extends LegemiddelTypeA implements Liniment{
	private String form;
	
	public LinimentTypeA(int iNr, String n, String f, String t, double p, int m, int s){
		super(iNr, n, t, p, m, s); // kaller på konstruktøren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}
