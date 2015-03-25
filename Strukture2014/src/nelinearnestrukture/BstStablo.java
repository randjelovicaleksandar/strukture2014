package nelinearnestrukture;

/**
 * Klasa koja implementira BST stablo. BST stablo je binarno stablo optimizovano
 * za pretragu (Binarno Stablo Trazenja ili Binary Search Tree). Kod ovog stabla
 * su elementi u njemu rasporedjeni tako da za svaki cvor vazi da su vrednosti u
 * njegovom levom podstablu manji od vrednosti u tom cvoru, a vrednosti u
 * njegovom desnom podstablu vece od vrednosti u tom cvoru Posto je ovo binarno
 * stablo, ova klasa nasledjuje klasu BinarnoStablo.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2012
 */
public class BstStablo extends BinarnoStablo {

	/**
	 * Glavna (javna) metoda za dodavanje novog elementa u stablo. Poziva
	 * privatnu metodu sa odgovarajuÄ‡im parametrima.
	 * 
	 * @param podatak
	 *            Podatak koji se ubacuje u stablo.
	 */
	public void ubaciUStablo(int podatak) {
		ubaci(koren, podatak);
	}

	/**
	 * Metoda koja implementira algoritam za ubacivanje novog elementa u BST
	 * stablo. Nov element se uvek ubacuje kao novi list u stablu, a mesto mu se
	 * odredjuje uporedjivanjem vrednosti sa vrednoscu u trenutnom cvoru.
	 * 
	 * @param k
	 *            Pokazivac na tekuci cvor u stablu. Novi podatak ubacujemo u
	 *            njegovo levo ili desno podstablo
	 * @param podatak
	 *            Element koji se ubacuje u stablo
	 */
	private void ubaci(CvorStabla k, int podatak) {
		if (praznoStablo())
			koren = new CvorStabla(podatak);
		else {
			// proveravamo da li je vrednost podatka koju ubacujemo
			// veca ili manja od vrednosti u tekucem cvoru
			if (podatak < k.podatak) {
				// ako je vrednost manja onda se novi element ubacuje u levo
				// podstablo
				if (k.levo != null)
					// ubacivanje u levo podstablo
					ubaci(k.levo, podatak);
				else
					// ako ne postoji levo dete, onda se novi cvor ubacuje levo
					// od tekuceg
					k.levo = new CvorStabla(podatak);
			} else if (podatak > k.podatak) {
				// ako je vrednost veca onda se novi element ubacuje u desno
				// podstablo
				if (k.desno != null)
					// ubacivanje u desno podstablo
					ubaci(k.desno, podatak);
				else
					// ako ne postoji desno dete, onda se novi cvor ubacuje
					// desno od tekuceg
					k.desno = new CvorStabla(podatak);
			}
			// ako je podatak jednak podatku u tekucem cvoru ne vrsi se
			// nikakva operacija
			// jer stablo ne moze da ima duple vrednosti
		}
	}

	/**
	 * Pretrazivanje BST stabla. Koristi se organizacija stabla kako bi se sto
	 * brze doslo do trazenog elementa
	 * 
	 * @param tekuci
	 *            Pokazivac na tekuci element u stablu
	 * @param podatak
	 *            Podatak koji se trazi
	 * @return Pokazivac na cvor koji ima trazenu vrednost. Ako cvor ne postoji,
	 *         metoda vraca null.
	 */
	CvorStabla pronadjiR(CvorStabla tekuci, int podatak) {
		// ako smo stigli do "kraja" stabla (prvi uslov) ili smo pronasli cvor
		if (tekuci == null || tekuci.podatak == podatak)
			return tekuci;

		if (podatak < tekuci.podatak)
			return pronadji(tekuci.levo, podatak);
		else
			return pronadji(tekuci.desno, podatak);
	}

	/**
	 * Pretrazivanje BST stabla. Koristi se organizacija stabla kako bi se sto
	 * brze doslo do trazenog elementa
	 * 
	 * @param tekuci
	 *            Pokazivac na tekuci element u stablu
	 * @param podatak
	 *            Podatak koji se trazi
	 * @return Pokazivac na cvor koji ima trazenu vrednost. Ako cvor ne postoji,
	 *         metoda vraca null.
	 */
	CvorStabla pronadji(CvorStabla tekuci, int podatak) {
		while (tekuci != null) {
			if (tekuci.podatak == podatak)
				return tekuci;
			if (tekuci.podatak < podatak) {
				tekuci = tekuci.desno;
			} else {
				tekuci = tekuci.levo;
			}
		}
		
		return null;
	}

	/**
	 * Pronalazak roditeljskog cvora za trenutni cvor
	 * 
	 * @param koren
	 *            Pokazivac na tekuci cvor
	 * @param tekuci
	 *            Podatak za koji se trazi roditeljski cvor
	 * @return Pokazivac na roditeljski cvor. Ako se podatak ne nalazi u stablu
	 *         ili ako se podatak nalazi u korenu, vraca null.
	 */

	public CvorStabla nadjiRoditelja(CvorStabla koren, CvorStabla tekuci) {
		if (koren == null || koren == tekuci)
			return null;

		if (tekuci.podatak < koren.podatak) {
			// ako se podatak nalazi u levom detetu, onda je tekuci njegov
			// roditelj
			if (koren.levo != null && koren.levo == tekuci)
				return koren;
			// probaj da pronadjes element u levom podstablu
			return nadjiRoditelja(koren.levo, tekuci);
		} else {
			// ako se podatak nalazi u desnom detetu, onda je tekuci njegov
			// roditelj
			if (koren.desno != null && koren.desno == tekuci)
				return koren;
			// probaj da pronadjes element u desnom podstablu
			return nadjiRoditelja(koren.desno, tekuci);
		}
	}
	
	/**
	 * Metoda za pronalazak cvora koji sadrzi maksimalnu vrednost u stablu.
	 */
	public CvorStabla maxCvor(CvorStabla tekuci) {
		while (tekuci.desno != null)
			tekuci = tekuci.desno;
		return tekuci;
	}

	/**
	 * Metoda izbacuje zadati element stabla koji je list ili polulist
	 * 
	 * @param cvor
	 */
	private void izbaciListPolulist(CvorStabla cvor) {
		// nalazimo roditelja za zadati element
		CvorStabla r = nadjiRoditelja(koren, cvor);
		// nalazimo dete (ako postoji) zadatog elementa

		CvorStabla dete = null;
		// ****************** prvi nacin *****************************
		dete = cvor.levo != null ? cvor.levo : cvor.desno;

		// ****************** drugi nacin *****************************
		// if (cvor.levo != null) {
		// dete = cvor.levo;
		// } else {
		// dete = cvor.desno;
		// }
		// ****************** kraj drugog nacin *****************************

		// ako ne postoji roditelj, to znaci da je element koji se izbacuje
		// koren stabla
		if (r == null)
			koren = dete;
		else {
			if (r.levo == cvor)
				r.levo = dete;
			else
				r.desno = dete;
		}
	}

	/**
	 * Metoda izbacuje element iz BST stabla koji sadrži zadatu vrednost
	 * 
	 * @param podatak
	 *            zadata vrednost za izbacivanje elementa
	 */
	public void izbaci(int podatak) {
		// pronalazimo cvor koji sadrzi zadati podatak
		CvorStabla cvor = pronadji(koren, podatak);
		// ako je stablo prazno ili ne postoji cvor koji sadrzi zadatu vrednost
		if (cvor == null)
			return;

		// ako cvor postoji, proveravamo da li ima oba deteta
		if (cvor.levo != null && cvor.desno != null) {
			// ako ima oba deteta, trazimo najveci element u njegovom levom
			// podstablu
			CvorStabla maxL = maxCvor(cvor.levo);
			// zamenjujemo vrednost
			cvor.podatak = maxL.podatak;
			// i izbacujemo najveci levi element
			izbaciListPolulist(maxL);

		} else {
			// ako element nema oba deteta, izbacujemo taj element
			izbaciListPolulist(cvor);
		}
	}

}
