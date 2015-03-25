package nelinearnestrukture;

/**
 * Klasa koja implementira ponasanje binarnog stabla. Ovo je dinamicka
 * razgranata struktura kod koje svaki element ima najvise jednog prethodnika i
 * najvise dva sledbenika. Elementi mogu da se dodaju bilo gde u strukturu i
 * bilo koji element moze da se izbaci. Redosled ubacivanja i izbacivanja nije
 * eksplicitno definisan.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2012
 */
public class BinarnoStablo {
	// Pokazivac na koreni (prvi) cvor u stablu.
	public CvorStabla koren;

	public BinarnoStablo() {
		koren = null;
	}

	/**
	 * Proverava da li je stablo prazno. Stablo je prazno kada ne postoji
	 * pokazivac na koren.
	 * 
	 * @return true ako je stablo prazno. u suprotnom false
	 */
	public boolean praznoStablo() {
		return koren == null;
	}

	/**
	 * Primer implementacije prefiksnog prolaza kroz stablo. U ovom primeru se
	 * vrsi ispisivanje elemenata u prefiksnom redosledu. Stablo se obilazi u
	 * redosledu koren - levo - desno. Levo i desno podstablo se obilaze na isti
	 * nacin.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla koje se obilazi.
	 */
	public void prefiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;

		// ispisi koren (trenutni element)
		System.out.println(cvor.podatak);
		// obidji prefiksno levo podstablo
		prefiksniProlaz(cvor.levo);
		// obidji prefiksno desno podstablo
		prefiksniProlaz(cvor.desno);
	}

	/**
	 * Primer implementacije infiksnog prolaza kroz stablo. U ovom primeru se
	 * vrsi ispisivanje elemenata u infiksnom redosledu. Stablo se obilazi u
	 * redosledu levo - koren - desno. Levo i desno podstablo se obilaze na isti
	 * nacin.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla koje se obilazi.
	 */
	public void infiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;

		// obidji infiksno levo podstablo
		infiksniProlaz(cvor.levo);
		// ispisi koren (trenutni element)
		System.out.println(cvor.podatak);
		// obidji infiksno desno podstablo
		infiksniProlaz(cvor.desno);
	}

	/**
	 * Primer implementacije postfiksnog prolaza kroz stablo. U ovom primeru se
	 * vrsi ispisivanje elemenata u postfiksnom redosledu. Stablo se obilazi u
	 * redosledu levo - desno - koren. Levo i desno podstablo se obilaze na isti
	 * nacin.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla koje se obilazi.
	 */
	public void postfiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;

		// obidji postfiksno levo podstablo
		postfiksniProlaz(cvor.levo);
		// obidji postfiksno desno podstablo
		postfiksniProlaz(cvor.desno);
		// ispisi koren (trenutni element)
		System.out.println(cvor.podatak);
	}

	/**
	 * Metoda za prebrojavanje cvorova u stablu. Prolazi kroz celo stablo na
	 * prefiksni nacin i pri obilasku svakog cvora dodaje 1 na ukupan broj
	 * cvorova.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla za koje trazimo broj elemenata
	 * @return Broj cvorova u stablu.
	 */
	public int brojElemenata(CvorStabla cvor) {
		if (cvor == null)
			return 0;

		// broj cvorova u stablu je jednak
		// koren (1) + broj elemenata u levom podstablu + broj elemenata u
		// desnom podstablu
		// ****************** prvi nacin *****************************
		// int br = 1; // racunaj trenutni cvor
		// dodaj broj elemenata u levom podstablu
		// br += brojElemenata(cvor.levo);
		// dodaj broj elemenata u desnom podstablu
		// br += brojElemenata(cvor.desno);
		// return br;

		// ****************** drugi nacin *****************************
		return 1 + brojElemenata(cvor.levo) + brojElemenata(cvor.desno);
	}

	/**
	 * Metoda za izracunavanje zbira elemenata cvorova stabla. Ako je stablo
	 * prazno, baca Exception. Prolazi kroz celo stablo na prefiksni nacin i pri
	 * obilasku svakog cvora dodaje njegovu vrednost na ukupan zbir.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla za koje trazimo zbir elemenata
	 * @return Zbir elemenata cvorova u stablu.
	 * @throws Exception
	 *             - ako je stablo prazno
	 */
	public int zbirElemenata(CvorStabla cvor) throws Exception {
		if (cvor == null)
			throw new Exception("Stablo je prazno");
		int suma = cvor.podatak;
		int a = 0;
		int b = 0;
		// ako postoji levo, sabiramo levo podstablo
		if (cvor.levo != null)
			a = zbirElemenata(cvor.levo);
		// ako postoji desno, sabiramo desno podstablo
		if (cvor.desno != null)
			b = zbirElemenata(cvor.desno);

		// ****************** drugi nacin *****************************
		// zbir elemenata cvorova u stablu je jednak
		// vrednost u korenu + zbir elemenata u levom podstablu + zbir elemenata
		// u desnom podstablu

		return suma + a + b;
	}

	/**
	 * Metoda za pronalazak maksimalne vrednosti u stablu. Rekurzivno pronalazi
	 * maksimalni element u levom podstablu i maksimalni element u desnom
	 * podstablu. Nakon toga uporedjuje te dve vrednosti sa vrednoscu u
	 * trenutnom cvoru i vraca najvecu vrednost od te tri vrednosti.
	 * 
	 * @param cvor
	 *            Pokazivac na koren stabla za koje trazimo maksimalni element
	 * @return Vrednost maksimalnog elementa u stablu.
	 */
	public int maxVrednost(CvorStabla cvor) {
		if (cvor == null)
			return Integer.MIN_VALUE;

		// ****************** prvi nacin *****************************
		int max = cvor.podatak;
		// nadji maksimalnu vrednost u levom podstablu
		int maxl = maxVrednost(cvor.levo);
		// nadji maksimalnu vrednost u desnom podstablu
		int maxd = maxVrednost(cvor.desno);

		// uporedjivanje vrednosti. max ce uvek uzimati vecu vrednost
		if (max < maxl)
			max = maxl;

		if (max < maxd)
			max = maxd;

		return max;

		// ****************** drugi nacin *****************************
		// return Math.max(Math.max(cvor.podatak, maxElement(cvor.levo)),
		// maxElement(cvor.desno));
	}

	/**
	 * Metoda za pronalazak minimalne vrednosti u stablu.
	 * 
	 * @param cvor
	 * @return minimalna vrednost u stablu
	 */
	public int minVrednost(CvorStabla cvor) {
		if (cvor == null)
			return Integer.MAX_VALUE;

		return Math.min(cvor.podatak,
				Math.min(minVrednost(cvor.levo), minVrednost(cvor.desno)));
	}

	/**
	 * Metoda za pronalazak cvora koji sadrzi maksimalnu vrednost u stablu.
	 * Rekurzivno pronalazi maksimalni element u levom podstablu i maksimalni
	 * element u desnom podstablu. Nakon toga uporedjuje te dve vrednosti sa
	 * vrednoscu u trenutnom cvoru i vraca cvor sa najvecom vrednoscu od te tri
	 * vrednosti.
	 * 
	 * @param cvor
	 *            - Pokazivac na koren stabla za koje trazimo maksimalni element
	 * @return pokazivac na cvor koji sadrzi maksimalni element u stablu.
	 */

	public CvorStabla maxCvor(CvorStabla cvor) {
		if (cvor == null)
			return null;

		CvorStabla max = cvor;
		// nadj maksimalnu vrednost u levom podstablu
		CvorStabla maxl = maxCvor(cvor.levo);
		// nadj maksimalnu vrednost u desnom podstablu
		CvorStabla maxd = maxCvor(cvor.desno);

		// uporedjivanje vrednosti. max ce uvek uzimati vecu vrednost
		if (maxl != null && max.podatak < maxl.podatak)
			max = maxl;

		if (maxd != null && max.podatak < maxd.podatak)
			max = maxd;

		return max;

	}

	int visina(CvorStabla cvor) {
		if (cvor == null)
			return 0;

		return 1 + Math.max(visina(cvor.levo), visina(cvor.desno));
	}

	CvorStabla pronadji(CvorStabla tekuci, int podatak) {
		if (tekuci == null || tekuci.podatak == podatak)
			return tekuci;

		CvorStabla l = pronadji(tekuci.levo, podatak);
		if (l != null)
			return l;

		// ****************** prvi nacin *****************************
		// CvorStabla d = pronadji(tekuci.desno, podatak);
		// if (d != null)
		// return d;
		// return null;

		// ****************** drugi nacin *****************************
		return pronadji(tekuci.desno, podatak);

	}

	/**
	 * Pronalazak roditeljskog cvora za trenutni cvor
	 * 
	 * @param tekuci
	 *            Pokazivac na tekuci cvor
	 * @param podatak
	 *            Podatak za koji se trazi roditeljski Äcvor
	 * @return PokazivaÄc na roditeljski Äcvor. Ako se podatak ne nalazi u
	 *         stablu ili ako se podatak nalazi u korenu, vraca null.
	 */

	public CvorStabla nadjiRoditelja(CvorStabla tekuci, int podatak) {
		if (tekuci == null || tekuci.podatak == podatak)
			return null;

		// ako se podatak nalazi u levom ili desnom detetu, onda je tekuci
		// njegov roditelj
		if ((tekuci.levo != null && tekuci.levo.podatak == podatak)
				|| (tekuci.desno != null && tekuci.desno.podatak == podatak))
			return tekuci;

		// probaj da pronadjes element u levom podstablu
		CvorStabla s = pronadji(tekuci.levo, podatak);
		// ako element jeste u levom
		if (s != null)
			// idi u levo podstablo
			return nadjiRoditelja(tekuci.levo, podatak);
		// u suprotnom probaj da pronadjes element u levom podstablu
		return nadjiRoditelja(tekuci.desno, podatak);
	}

}
