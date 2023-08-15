/* 
 * Dette er et generisk grensesnitt for beholderen som skal inneholde objekter av type Leger. 
 * Denne beholderen skal være en lenket liste og beholderen skal kunne
 * kunne inneholde elementer som implementerer grensesnittene
 * Comparable (med seg selv) og Lik. 
 */

interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik> {
	void leggInnSortert(T en); // sette inn et nytt element (i sortert rekkefølge, minste først)
	T finnObjekt(String s); // finne et element basert på en nøkkel av typen String
	Iterable<T> itererGjennomAlle(); // returnere en Iterator over listen, slik at innholdet kan
									// bli listet opp i sortert rekkefølge, minste først.
}