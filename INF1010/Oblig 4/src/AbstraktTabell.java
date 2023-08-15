/* 
 * Dette er et generisk grensesnitt for beholderene som skal inneholde objekter av type Legemidler og Personer.
 * Disse beholderene skal v�re tabeller og det er ingen restriksjoner
 *  p� hva slags elementer den abstrakte tabellen skal kunne inneholde. 
 */

interface AbstraktTabell<T> { 
	boolean leggInn(T en, int indeks); 	// sette et objekt inn i tabellen p� en oppgitt plass (indeks). Metoden returnerer sann
										// eller usann avhengig om operasjonen gikk bra eller ikke
	T finnObjekt(int indeks); 			// finn et objekt basert p� en indeks
	Iterable<T> itererGjennomAlle(); 	// returnere en Iterator over tabellen
}
