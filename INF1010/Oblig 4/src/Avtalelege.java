/*
 * Et grensesnitt som beskriver om vanlige leger og spesialister er avtaleleger
 */

interface Avtalelege {
	int hentAvtalenummer(); // avtalenummeret er 0 dersom legen er ikke en avtalelege
}
