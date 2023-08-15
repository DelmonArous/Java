/*
 * Dette grensesnittet skal superklassen Legemidler implementerer og
 * henter de mest generelle egenskapene legemidler har.
 */

public interface Legemiddel {
	String hentLegemiddelNavn();  	// henter navnet på produktet
	double hentPris();				// henter prisen på produktet
	void endrePris(double p);		// endrer prisen på produktet
	int hentIDnummer();				// henter id-nummeret på produktet
	String hentType();
}