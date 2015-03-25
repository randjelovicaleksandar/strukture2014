package linearnestrukture.dinamicke.dslista;

/**
 * Cvor dvostruko spregnute liste. Svaki cvor liste u sebi cuva podatak (kod
 * ovog cvora to je ceo broj) i pokazivace na prethodni i sledeci cvor.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class CvorDSListe {
	// podatak koji se cuva u cvoru.
	// Ako cvor treba da cuva neki drugi tip elementa, ovde to treba promeniti
	int podatak;

	// "pokazivac" na prethodni cvor u listi
	CvorDSListe prethodni;

	// "pokazivac" na sledeci cvor u listi
	CvorDSListe sledeci;

	/**
	 * Konstruktor. Prihvata i postavlja sve elemente cvora.
	 * 
	 * @param p Podatak koji se cuva u cvoru.
	 * @param pret Pokazivac na prethodni element liste
	 * @param sled Pokazivac na sledeći element za cvor koji se kreira
	 * 
	 */
	public CvorDSListe(int p, CvorDSListe pret, CvorDSListe sled) {
		podatak = p;
		prethodni = pret;
		sledeci = sled;
	}
}
