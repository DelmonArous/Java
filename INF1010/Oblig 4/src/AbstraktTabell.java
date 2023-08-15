/* 
 * Dette er et generisk grensesnitt for beholderene som skal inneholde objekter av type Legemidler og Personer.
 * Disse beholderene skal være tabeller og det er ingen restriksjoner
 *  på hva slags elementer den abstrakte tabellen skal kunne inneholde. 
 */

interface AbstraktTabell<T> { 
	boolean leggInn(T en, int indeks); 	// sette et objekt inn i tabellen på en oppgitt plass (indeks). Metoden returnerer sann
										// eller usann avhengig om operasjonen gikk bra eller ikke
	T finnObjekt(int indeks); 			// finn et objekt basert på en indeks
	Iterable<T> itererGjennomAlle(); 	// returnere en Iterator over tabellen
}
