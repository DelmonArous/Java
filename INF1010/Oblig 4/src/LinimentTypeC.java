/*
 * Subklasse av LegemiddelTypeC som beskriver narkotiske legemidler som
 * har liniment som form. Dette ved å implementere grensesnittet Liniment.
 */

public class LinimentTypeC extends LegemiddelTypeC implements Liniment{
	private String form;
	
	public LinimentTypeC(int iNr, String n, String f, String t, double p, int m){
		super(iNr, n, t, p, m); // kaller på konstruktøren i superklassen Legemidler
		form = f;
	}
	
	public String hentForm(){
		return form;
	}
}