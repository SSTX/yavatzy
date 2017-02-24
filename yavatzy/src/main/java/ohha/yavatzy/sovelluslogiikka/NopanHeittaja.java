/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import ohha.yavatzy.sovelluslogiikka.domain.Noppa;

/**
 * Luokka huolehtii yatzy-pelin noppien heittämisestä.
 * @author ttiira
 */
public class NopanHeittaja {

    private List<Noppa> nopat;
    private List<Noppa> valitutNopat;
    private int heittojaJaljella;

    /**
     * Luodaan nopanheittäjä.
     * @param nopat nopat, joita tämä olio heittää.
     */
    public NopanHeittaja(List<Noppa> nopat) {
        this.nopat = new ArrayList<>();
        this.valitutNopat = new ArrayList<>();
        if (nopat == null || nopat.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                this.nopat.add(new Noppa());
            }
        } else {
            this.nopat = nopat;
        }
        this.heittojaJaljella = 2;
    }

    public List<Noppa> getNopat() {
        return nopat;
    }

    public List<Noppa> getValitutNopat() {
        return valitutNopat;
    }

    /**
     * Lisää nopan heitettäväksi merkittyjen listalle.
     *
     * @param noppa lisättävä noppa
     */
    public void valitseNoppa(Noppa noppa) {
        this.getValitutNopat().add(noppa);
    }

    /**
     * Poistaa nopan heitettäväksi merkittyjen listalta.
     *
     * @param poistettava poistettava noppa
     */
    public void poistaNopanValinta(Noppa poistettava) {
        this.getValitutNopat().remove(poistettava);
    }

    /**
     * Asetetaan jäljellä olevien heittojen määrä kolmeen. Heitetään kaikki nopat.
     */
    public void seuraavaVuoro() {
        this.valitutNopat = new ArrayList<>(this.nopat);
        this.heittojaJaljella = 3;
        this.heitaValitutNopat();
    }

    /**
     * Heitetään ne nopat, jotka oliomuuttuja määrää heitettäviksi. Vähennetään
     * vuoron jäljellä olevien heittojen määrää yhdellä. Poistetaan kaikkien
     * noppien valinta.
     */
    public void heitaValitutNopat() {
        if (this.heittojaJaljella()) {
            for (Noppa noppa : this.getValitutNopat()) {
                noppa.heita();
            }
            this.valitutNopat.clear();
            this.heittojaJaljella--;
        }
    }

    /**
     * Kertoo, voiko vuorossa oleva pelaaja heittää vielä noppia.
     *
     * @return true, jos yatzyn säännöt sallivat noppien heittämisen. false,
     * muuten.
     */
    public boolean heittojaJaljella() {
        return this.heittojaJaljella > 0;
    }
    
    /**
     * Kertoo, onko noppia valittu heitettäväksi.
     * @return true jos on valittu noppia, false muuten
     */
    public boolean noppiaValittu() {
        return !this.valitutNopat.isEmpty();
    }
}
