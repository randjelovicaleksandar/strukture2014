package linearnestrukture.dinamicke.jslista;

/**
 * Jednostruko spregnuta lista. Dinamicka opsta lista - elementi mogu da se
 * dodaju bilo gde u strukturu i bilo koji element moze da se izbaci. Redosled
 * ubacivanja i izbacivanja nije definisan. Elementi su uvezani preko jedne veze
 * "unapred", tako da je moguce direktno kretanje kroz strukturu samo u jednom
 * smeru od prvog do poslednjeg. Na svaki cvor pokazuje tacno jedan pokazivac,
 * te je pri ubacivanju novog cvora potrebno povezati jedan pokazivac na njega,
 * dok je pri izbacivanju potrebno razvezati jedan pokazivac.
 * 
 * @author Dejan Stojimirovic, Strukture podataka i algoritmi, FON, 2014
 * 
 */
public class JSLista {
	/**
	 * Pokazivav na prvi cvor u listi. Posto su cvorovi medjusobno uvezani,
	 * dovoljno je pamtiti gde se nalazi prvi cvor, pa preko njega pristupiti
	 * ostalim cvorovima.
	 */
	public CvorJSListe prvi;

	public JSLista() {
		prvi = null;
	}

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
		// Posto kreiramo dinamicku strukturu, nemamo ogranicenje na broj
		// elemenata koji moze da se smesti u nju, pa samim tim ne moramo da
		// proveravamo da li je puta

		// ****************** prvi nacin *****************************
		// Kreiranje novog cvora. Posto se novi cvor kreira na pocetku
		// strukture, njegov sledeci element ce biti trenutno prvi element. Ako
		// je lista prazna, sledeci element od novog ce biti null. Posto je
		// lista prazna, to znaci da je i prvi jednak null, pa samim tip i tu
		// situaciju obuhvatamo na ovaj nacin
		CvorJSListe novi = new CvorJSListe(podatak, prvi);
		// nakon kreiranja cvora postavlja se da je novokreirani cvor prvi cvor.
		prvi = novi;

		// ****************** drugi nacin *****************************
		// prvi = new CvorJSListe(podatak, prvi);

	}

	/**
	 * Ubacivanje novog elementa na kraj liste. Potrebno je naci poslednji cvor
	 * i ubaciti novi nakon njega.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciNaKraj(int podatak) {
		if (praznaLista()) {
			// ako je lista prazna, algoritam za ubacivanje novog cvora na kraj
			// liste je isti kao i za ubacivanje na pocetak, jer se ubacuje
			// prvi koji je istovremeno i poslednji cvor
			ubaciNaPocetak(podatak);
		} else {
			// pomocni pokazivac preko koga se krece kroz listu
			CvorJSListe pom = prvi;
			// pronalazak poslednjeg cvora u listi (onaj cvor koji nema
			// sledbenika)
			while (pom.sledeci != null) {
				pom = pom.sledeci;
			}
			// kreiranje novog cvora. Posto se ubacuje na kraj liste,
			// onda on nema sledbenika.
			CvorJSListe novi = new CvorJSListe(podatak, null);
			// povezivanje da je novokreirani cvor sledbenik do tada poslednjem
			// cvoru
			pom.sledeci = novi;
		}
	}

	/**
	 * Izbacivanje elementa sa pocetka liste. Element se izbacuje tako sto se
	 * pokazivac na prvi cvor preveze da pokazuje na drugi cvor. Time niko vise
	 * ne pokazuje na taj cvor cime se on brise iz memorije.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak.
	 */
	public int izbaciSaPocetka() throws Exception {
		// Prvo moramo proveriti da li u listi ima elemenata. Ako nema
		// elemenata, ne mozemo nista izbaciti.
		// Ako je lista prazna baca se izuzetak.
		if (praznaLista())
			throw new Exception("lista je prazna");

		// sacuvamo podatak iz prvog cvora u pomocnu promenljivu
		int podatak = prvi.podatak;
		// prebacujemo prvi na drugi cvor
		prvi = prvi.sledeci;
		// vracamo sacuvani podatak
		return podatak;
	}

	/**
	 * Izbacivanje elementa sa kraja liste. Da bismo izbacili poslednji cvor
	 * potrebno je da nadjemo pretposlednji cvor i da razvezemo vezu izmedju
	 * pretposlednjeg i poslednjeg cvora.
	 * 
	 * @return Vrednost iz cvora koji je izbacen.
	 * @throws Exception
	 *             Ako je lista prazna baca se izuzetak
	 */
	public int izbaciSaKraja() throws Exception {
		// Prvo moramo proveriti da li u listi ima elemenata. Ako nema
		// elemenata, ne mozemo nista izbaciti.
		// Ako je lista prazna baca se izuzetak.
		if (praznaLista())
			throw new Exception("lista je prazna");

		// ako lista ima samo jedan element, onda se izbacuje prvi
		if (prvi.sledeci == null)
			return izbaciSaPocetka();

		// pronalazimo pretposlednji cvor
		CvorJSListe pom = prvi;
		// pretposlednji cvor ima samo jedan element ispred sebe
		while (pom.sledeci.sledeci != null) {
			pom = pom.sledeci;
		}
		int podatak = pom.sledeci.podatak;
		// razvezujemo vezu izmedju pretposlednjeg i poslednjeg
		pom.sledeci = null;
		return podatak;
	}

	/**
	 * Ubacivanje elementa u sortiranu listu tako da ona ostane sortirana u
	 * rastucem redosledu. Prolazi se kroz listu i trazi onaj cvor kod koga je
	 * podatak u njegovom sledecem veci od elementa koji se ubacuje. Nakon toga
	 * se novi cvor umetne izmedju trenutnog i njegovog sledeceg.
	 * 
	 * @param podatak
	 *            Podatak koji ce se cuvati u novom cvoru
	 */
	public void ubaciUSort(int podatak) {
		// ako je lista prazna ili ako je podatak u prvom cvoru nije manji od
		// podatka koji se ubacuje, onda se vrsi ubacivanje na pocetak liste
		if (praznaLista() || prvi.podatak >= podatak)
			prvi = new CvorJSListe(podatak, prvi);
		else {
			CvorJSListe pom = prvi;
			// trazimo element ciji sledeci cvor ima podatak koji je
			// veci od elementa koji ubacujemo ili dok ne dodjemo do kraja liste
			while (pom.sledeci != null && pom.sledeci.podatak < podatak) {
				pom = pom.sledeci;
			}
			// umetanje novog cvora
			CvorJSListe novi = new CvorJSListe(podatak, pom.sledeci);
			pom.sledeci = novi;
		}
	}

	/**
	 * Prolazak kroz listu i ispisivanje svih elemenata u cvorovima od prvog do
	 * poslednjeg.
	 */
	public void ispisi() {
		CvorJSListe pom = prvi;
		while (pom != null) {
			System.out.println(pom.podatak);
			pom = pom.sledeci;
		}
	}
	
	/**
	 * Izbacivanje elementa na koji pokazuje t. Potrebno je da se nadje element
	 * ciji je sledeci t. Ako t pokazuje na prvi, element se izbacuje tako sto
	 * se pokazivac na prvi cvor preveze da pokazuje na drugi cvor. U suprotnom,
	 * potrebno je naci element ciji je sledeci t. Element se izbacuje tako sto
	 * se njegov pokazivac preveze da ne pokazuje na t nego element posle t.
	 * 
	 * @param t
	 *            "pokazivac" na neki element u listi
	 * @return vrednost cvora koji je izbacen
	 * @throws Exception
	 */
	public int izbaciTrenutni(CvorJSListe t) throws Exception {
		// provera da li je lista prazna
		if (prvi == null)
			// ako je prazna, bacamo exception
			throw new Exception("Lista je prazna!");
		// provera da li t pokazuje na prvi
		if (t == prvi)
			// ako pokazuje, pomeramo prvi na sledeci element
			prvi = prvi.sledeci;
		else {
			// ako t ne pokazuje na prvi, uvodimo pomocni pokazivac p
			CvorJSListe p = prvi;
			// sve dok p.sledeci ne pokazuje na t
			while (p.sledeci != t) {
				// pomeramo p za po jedno mesto
				p = p.sledeci;
			}
			// kada pronadjemo element ciji je sledeci t
			// prevezemo tako da njegov sledeci ne pokazuje vise na t, nego na
			// element posle t
			p.sledeci = t.sledeci; // p.sledeci = p.sledeci.sledeci;
		}
		// vracamo vrednost elementa koji izbacujemo
		return t.podatak;

		// NAPOMENA: OVO JE SPECIJALAN SLUCAJ DA NE MORAMO UZETI VREDNOST IZ
		// ELEMENTA PRE NEGO STO GA IZBACIMO!!!!!
	}
	
	/**
	 * Dat je pokazivac na prvi element jednostruko-spregnute liste. Napisati
	 * metodu koja ispisuje elemente od poslednjeg do prvog
	 * 
	 * @param pom
	 *            pokazivac na prvi element u listi (koristimo ga kao pomocni)
	 */

	public void ispisiObrnuto(CvorJSListe pom) {
		// kada se metoda ne izvrsava
		if (pom == null) {
			return;
		}

		// kretanje kroz strukturu
		ispisiObrnuto(pom.sledeci);

		// code sta se radi sa JEDNIM ELEMENTOM
		System.out.println(pom.podatak);
	}
	
	/**
	 * Dat je pokazivac na neki element jednostruko-spregnute ciklicne liste.
	 * Napisati metodu koja racuna zbir elemenata u ciklicnoj listi. Ciklicna
	 * lista je lista gde poslednji element u listi pokazuje na prvi element u
	 * toj listi.
	 * 
	 * NAPOMENA: Ovde vise ne vazi da se krecemo kroz listu dok je pom != null,
	 * jer pom nikada nece biti null. Druga stvar na koju treba obratiti paznju
	 * je da se u petlji ne mogu obici svi element i uvek je potrebno jedan
	 * element "obraditi" van petlje. U konkretnom primeru taj element
	 * "obradjujemo" pre petlje. Element koji se mora "obraditi" van petlje je
	 * ILI element od koga krecemo ILI element sa kojim zavrsavamo
	 * 
	 * 
	 * @return zbir elemenata
	 * @throws Exception
	 */
	public int zbirElemenataUCiklicnoj() throws Exception {
		if (prvi == null)
			throw new Exception("Lista je prazna!");
		int s = prvi.podatak;
		CvorJSListe pom = prvi.sledeci;
		while (pom != prvi) {
			s += pom.podatak;
			pom = pom.sledeci;
		}
		return s;
	}
	
	/**
	 * Dat je pokazivac na prvi element jednostruko spregnute liste. Napisati
	 * metodu koja izbacuje pretposlednji element iz te liste
	 */

	public void pretposlednjiNaPrvoMesto() {
		// u slucaju da lista ima 0, 1 ili 2 elementa ne radimo nista
		if (prvi == null || prvi.sledeci == null
				|| prvi.sledeci.sledeci == null)
			return;

		// nalazimo element koji se nalazi pre elementa koji treba da premestimo
		CvorJSListe pom = prvi;
		while (pom.sledeci.sledeci.sledeci != null) {
			pom = pom.sledeci;
		}
		// postavljamo neki pomocni pokazivac da ne bi smo izgubili taj element
		// koji treba da premestimo
		CvorJSListe pretposlednji = pom.sledeci;

		// prevezujemo elemente
		pom.sledeci = pom.sledeci.sledeci;
		pretposlednji.sledeci = prvi;
		prvi = pretposlednji;
	}

	

	

}
