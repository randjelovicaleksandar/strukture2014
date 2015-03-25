package hashing;

public class Hashing {

	public int[] niz;
	public int brEl;

	public Hashing(int dimenzija) {
		niz = new int[dimenzija];
		brEl = 0;
	}

	/**
	 * Metoda ubacuje podatak u hash niz
	 * 
	 * @param podatak
	 */
	public boolean ubaci(int podatak) {
		// ako je niz pun ili ako je niz.length == 0
		if (brEl == niz.length)
			return false;

		int adresa = hashFunkcija(podatak);

		while (niz[adresa] > 0)
			adresa = (adresa + 1) % niz.length;

		niz[adresa] = podatak;
		brEl++;
		return true;
	}

	/**
	 * Metoda trazi zadati podatak u hash nizu
	 * 
	 * @param podatak
	 * @return index elementa u nizu ako ga pronadje, a ako ga ne pronadje,
	 *         vraca -1
	 */
	public int pronadji(int podatak) {
		if (niz.length == 0 || brEl == 0)
			return -1;

		int adresa = hashFunkcija(podatak);
		int pom = adresa;
		while ((niz[adresa] != podatak) && (niz[adresa] != 0)) {
			adresa = (adresa + 1) % niz.length;
			if (adresa == pom)
				return -1;
		}

		if (niz[adresa] == podatak)
			return adresa;
		return -1;
	}

	/**
	 * metoda izbacuje prosledjeni podatak iz hash niza
	 * 
	 * @param podatak
	 */
	public void izbaci(int podatak) {
		int adresa = pronadji(podatak);

		if (adresa == -1)
			return;
		niz[adresa] = -1;
	}

	/**
	 * Hash funcija koja racuna adresu u nizu
	 * 
	 * @param podatak
	 * @return adresa u nizu
	 */
	public int hashFunkcija(int podatak) {
		return podatak % niz.length;
	}
}
