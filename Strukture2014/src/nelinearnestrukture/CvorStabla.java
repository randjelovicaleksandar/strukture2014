package nelinearnestrukture;

/**
 * Cvor binarnog stabla.
 * Svaki cvor binarnog stabla u sebi cuva podatak (kod ovog cvora to je ceo broj)
 * i pokazivace na levo i desno dete.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2013
 */
public class CvorStabla {
	// podatak koji se cuva u cvoru. 
	// Ako cvor treba da cuva neki drugi tip elementa, ovde to treba promeniti
	int podatak;

	// "pokazivac" na levi cvor (levo dete - koren levog podstabla)
	CvorStabla levo;
	// "pokazivac" na desni cvor (desno dete - koren desnog podstabla)
	CvorStabla desno;
	
	/**
	 * Konstruktor. Prihvata i postavlja sve elemente cvora.
	 * @param p Podatak koji se cuva u cvoru.
	 * @param l Pokazivac na levo dete
	 * @param d Pokazivac na desno dete
	 */
	public CvorStabla(int p, CvorStabla l, CvorStabla d){
		podatak = p;
		levo = l;
		desno = d;
	}
	
	/**
	 * Konstruktor. Inicijalizuje samo podatak. Ovaj konstruktor se poziva
	 * kada se kreira novi list u stablu.
	 * @param p Podatak koji se cuva u cvoru.
	 */
	public CvorStabla(int p){
		// poziva prethodni konstruktor i prosledjuje mu odgovarajuce parametre
		this(p, null, null);
	}
}
