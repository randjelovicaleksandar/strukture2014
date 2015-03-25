package linearnestrukture.dinamicke.dslista;

/**
 * Dvostruko spregnuta lista. Dinamicka opsta lista - elementi mogu da se dodaju
 * bilo gde u strukturu i bilo koji element moze da se izbaci. Redosled
 * ubacivanja i izbacivanja nije definisan. Elementi su uvezani preko dve veze
 * "unapred" i "unazad", tako da je moguce direktno kretanje kroz strukturu u
 * smeru od prvog do poslednjeg i od poslednjeg do prvog. Na svaki cvor pokazuje
 * tacno dva pokazivaca, te je pri ubacivanju novog cvora potrebno povezati dva
 * pokazivaca na njega, dok je pri izbacivanju potrebno razvezati dva
 * pokazivaca. Gledano sa oba kraja, lista je strukturno potpuno simetricna.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class DSLista {
	// Pokazivac na prvi cvor u listi.
	public CvorDSListe prvi;

	/**
	 * Proverava da li je lista prazna. Lista je prazna kada ne postoji
	 * pokazivac na prvi cvor.
	 * 
	 * @return true ako je lista prazna. u suprotnom false
	 */
	public boolean praznaLista() {
		return prvi == null;
	}

	/**
	 * Ubacivanje novog elementa na pocetak liste.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciNaPocetak(int podatak) {
		// Ubacujemo na pocetak liste
		prvi = new CvorDSListe(podatak, null, prvi);
		
		// Ako postoji sledeci element, prevezujemo da prethodni od sledeceg elementa
		// "pokazuje" na taj novokreirani element
		if(prvi.sledeci != null){
			prvi.sledeci.prethodni = prvi;
		}
	}

	/**
	 * Ubacivanje novog elementa na kraj liste.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciNaKraj(int podatak) {
		// Ako je lista prazna, novi element se ubacuje na pocetak (istovremeno je i prvi i 
		// poslednji element
		if(prvi == null){
			ubaciNaPocetak(podatak);
			return;
		}
		
		// Ako lista nije prazna, potrebno je da pronadjemo taj poslednji element
		CvorDSListe pom = prvi;
		while(pom.sledeci != null){
			pom = pom.sledeci;
		}
		// Nakon petlje, pom ostaje na poslednjem elementu u listi
		
		// Kreiranje novog cvora. Pocto se novi cvor kreira na kraju
		// strukture, on nema sledbenika, a njegov prethodnik ce biti
		// trenutno poslednji element.
		CvorDSListe novi = new CvorDSListe(podatak, pom, null);

		//novokreirani element sledbenik trenutno poslednjem elementu
		pom.sledeci = novi;
	}

	/**
	 * Izbacivanje elementa sa pocetka liste. Element se izbacuje tako sto se
	 * pokazivac na prvi cvor prevece da pokazuje na drugi cvor i razveze veza
	 * "unazad" izmedju drugog i prvog cvora. Time niko vise ne pokazuje na taj
	 * cvor cime se on brise iz memorije.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak.
	 */
	public int izbaciSaPocetka() throws Exception {
		// Ako je lista prazna baca se izuzetak
		if (praznaLista()){
			throw new Exception("lista je prazna");
		}

		// sacuvamo podatak iz prvog cvora u pomocnu promenljivu
		int podatak = prvi.podatak;
		// prebacujemo prvi na drugi cvor
		prvi = prvi.sledeci;
		// ako je lista imala samo jedan element, onda je sada prvi == null,
		// pa treba postaviti i da je poslednji == null
		if (prvi != null){
			// razvezivanje veze unazad
			prvi.prethodni = null;
		}
		
		return podatak;
	}

	/**
	 * Izbacivanje elementa sa kraja liste. Element se izbacuje tako sto se
	 * pokazivac na poslednji cvor preveze da pokazuje na pretposlednji cvor i
	 * razvece veza "unapred" izmedju pretposlednjeg i poslednjeg cvora. Time
	 * niko vice ne pokazuje na taj cvor cime se on brise iz memorije.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak.
	 */
	public int izbaciSaKraja() throws Exception {
		// Ako je lista prazna baca se izuzetak
		if (praznaLista())
			throw new Exception("lista je prazna");
		
		// Ako postoji samo jedan element u listi. Moramo proveriti ovaj uslov jer je ovo jedina 
		// situacija gde se pomera i pokazivac sa prvog elementa
		if(prvi == null){
			int podatak = prvi.podatak;
			prvi = prvi.sledeci;
			return podatak;
		}
		
		CvorDSListe pom = prvi;
		while(pom.sledeci != null){
			pom = pom.sledeci;
		}
		
		// prebacujemo poslednji na pretposlednji cvor
		pom.prethodni.sledeci = null;		
		return pom.podatak;
	}

	/**
	 * Ubacivanje elementa u sortiranu listu tako da ona ostane sortirana u
	 * rastucem redosledu. Prolazi se kroz listu i trazi onaj cvor ciji je
	 * podatak veci od elementa koji se ubacuje. Nakon toga se novi cvor ubacuje
	 * izmedju trenutnog i njegovog prethodnika. Dat je pokazivac samo na prvi
	 * element liste.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciUSort(int podatak) {
		// Ako je lista prazna ili ako je podatak u prvom cvoru veci od
		// podatka koji se ubacuje, onda se vrsi ubacivanje na pocetak te liste.
		if (prvi == null || prvi.podatak >= podatak) {
			// i postavljamo da je taj novi sada prvi
			prvi = new CvorDSListe(podatak, null, prvi);

			// ako postoji u listi vise od jednog elementa
			if (prvi.sledeci != null)
				// povezujemo da drugi element pokazuje na novi
				prvi.sledeci.prethodni = prvi;

		} else {
			CvorDSListe pom = prvi;
			// trazimo element ciji je sledeci podatak veci od vrednosti koju
			// ubacujemo
			while (pom.sledeci != null && pom.sledeci.podatak < podatak) {
				pom = pom.sledeci;
			}

			// umetanje novog cvora
			CvorDSListe novi = new CvorDSListe(podatak, pom, pom.sledeci);
			if (pom.sledeci != null)
				pom.sledeci.prethodni = novi;

			pom.sledeci = novi;
		}
	}
	
	/**
	 * Dat je pokazivac na prvi element dvostruko-spregnute liste. Napisati
	 * metodu koja vraca pokazivac na cvor koji sadrzi najmanju vrednost u listi
	 * 
	 * @return pokazivac na cvor koji sadrzi najmanju vrednost u listi
	 */

	CvorDSListe nadjiMin() {
		CvorDSListe pom = prvi;
		CvorDSListe min = prvi;
		while (pom != null) {
			if (pom.podatak < min.podatak)
				min = pom;
			pom = pom.sledeci;
		}
		return min;
	}

	/**
	 * Dat je pokazivac na prvi element dvostruko-spregnute liste. Napisati
	 * metodu koja izbacuje cvor sa najmanjom vrednoscu iz liste.
	 * 
	 * @return vrednost koja se izbacuje
	 * @throws Exception ako je lista prazna
	 */

	public int izbaciMin() throws Exception {
		CvorDSListe min = nadjiMin();
		if (min == null)
			throw new Exception("Lista je prazna");

		if (min.prethodni != null)
			min.prethodni.sledeci = min.sledeci;
		else
			prvi = prvi.sledeci;

		if (min.sledeci != null)
			min.sledeci.prethodni = min.prethodni;

		return min.podatak;
	}
	
	/**
	 * Dat je pokazivac na prvi element dvostruko-spregnute liste. Napisati
	 * metodu koja vraca invertovanu listu. Dozvoljeno je koriscenje pomocnih
	 * struktura, zabranjeno je menjanje vrednosti cvorova
	 * 
	 * Invertovanje liste znaci: ako je lista 2 5 9 7 1, lista koja treba da se
	 * vrati je 1 7 9 5 2
	 * 
	 * @param p1
	 *            pokazivac na prvi element dvostruko-spregnute liste
	 * @return vraca pokazivac na prvi element invertovane liste
	 */
	public CvorDSListe invertuj(CvorDSListe p1) {
		// kreiramo pokazivac na prvi element druge liste
		CvorDSListe p2 = null;
		// sve dok postoje elementi u prvoj listi
		while (p1 != null) {
			// kreiram novi cvor sa podatkom iz postojece liste i dodajem taj
			// novi cvor na pocetak novokreirane liste

			// ************* metoda ubaci na pocetak DS liste *******
			p2 = new CvorDSListe(p1.podatak, null, p2);
			if (p2.sledeci != null)
				p2.sledeci.prethodni = p2;
			// ********************* kraj metode**********************
			// pomerimo pokazivac na sledeci element u vec postojecoj listi
			p1 = p1.sledeci;
		}
		// vracamo novokreiranu listu, koja predstavlja invertovanu postojecu
		return p2;
	}
	
	
}
