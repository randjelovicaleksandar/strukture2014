package hashing;

import linearnestrukture.dinamicke.jslista.CvorJSListe;

public class HasningOlancavanjem {

	public CvorJSListe[] niz;

	public HasningOlancavanjem(int dimenzija) {
		niz = new CvorJSListe[dimenzija];
	}

	/**
	 * Metoda ubacuje podatak u hash niz
	 * 
	 * @param podatak
	 */
	public void ubaci(int podatak) {
		if (niz.length == 0)
			return;

		int adresa = hashFunkcija(podatak);
		niz[adresa] = new CvorJSListe(podatak, niz[adresa]);
	}

	/**
	 * Metoda trazi prosledjenu vrednost
	 * 
	 * @param podatak
	 * @return vraca index elementa u nizu, a ako ne postoji, vraca -1
	 */
	public boolean pronadji(int podatak) {
		if (niz.length == 0)
			return false;

		int adresa = hashFunkcija(podatak);		
		CvorJSListe pom = niz[adresa];
		while ((pom != null) && (pom.podatak != podatak)){
			pom = pom.sledeci;
		}

		if (pom == null)
			return false;
		return true;
	}

	/**
	 * Metoda trazi prosledjenu vrednost
	 * 
	 * @param podatak
	 * @return vraca CvorJSLIste, ako ne postoji, vraca null
	 */
	public CvorJSListe pronadjiCvor(int podatak) {
		if (niz.length == 0)
			return null;

		int adresa = hashFunkcija(podatak);
		if (niz[adresa] == null)
			return null;
		CvorJSListe pom = niz[adresa];
		while ((pom != null) && (pom.podatak != podatak))
			pom = pom.sledeci;
		return pom;
	}

	/**
	 * metoda izbacuje prosledjeni podatak
	 * 
	 * @param podatak
	 */
	public void izbaci(int podatak) {
		int adresa = hashFunkcija(podatak);

		CvorJSListe pom = niz[adresa];
		if (pom.podatak == podatak)
			niz[adresa] = pom.sledeci;
		else {
			while (pom.sledeci != null && pom.sledeci.podatak != podatak){
				pom = pom.sledeci;
			}
			
			if (pom.sledeci != null)
				pom.sledeci = pom.sledeci.sledeci;
		}
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
