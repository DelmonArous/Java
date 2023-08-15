/*
 * Dette grensesnittet skal superklassen Legemidler implementerer og
 * henter de mest generelle egenskapene legemidler har.
 */

public interface Legemiddel {
	String hentLegemiddelNavn();  	// henter navnet p� produktet
	double hentPris();				// henter prisen p� produktet
	void endrePris(double p);		// endrer prisen p� produktet
	int hentIDnummer();				// henter id-nummeret p� produktet
	String hentType();
}