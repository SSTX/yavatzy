/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pelin logiikka. Pitää kirjaa vuoroista, kierrosnumerosta, pelaajista. Tietää
 * myös noppien heittämiseen liittyvät säännöt.
 */
public class Peli {

    private List<Pelaaja> pelaajat;
    private int vuoroNumero;
    private Pistelista pistelista;
    private int kierros;
    private int kierroksienKokonaismaara;
    private Pisteytyssaannot saannot;
    private List<Noppa> nopat;
    private int heittojaJaljella;

    /**
     * Nämä nopat heitetään, kun kutsutaan pelin metodia heitaNopat()
     */
    private List<Noppa> valitutNopat;

    /**
     * Luodaan peli annetuilla nopilla.
     *
     * @param nopat pelin käyttämät nopat
     */
    public Peli(List<Noppa> nopat) {
        this.kierros = 1;
        this.kierroksienKokonaismaara = 15;
        this.pelaajat = new ArrayList<>();
        this.pistelista = new Pistelista();
        this.saannot = new Pisteytyssaannot();
        this.valitutNopat = new ArrayList<>();
        this.heittojaJaljella = 3;
        if (nopat == null) {
            nopat = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                nopat.add(new Noppa(6));
            }
        }
        this.nopat = nopat;
    }

    /**
     * Luodaan peli oletusnopilla.
     */
    public Peli() {
        this(null);
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public Pistelista getPistelista() {
        return pistelista;
    }

    public int getVuoroNumero() {
        return vuoroNumero;
    }

    public List<Noppa> getValitutNopat() {
        return this.valitutNopat;
    }

    /**
     * Kertoo, voiko vuorossa oleva pelaaja heittää vielä noppia.
     *
     * @return true, jos yatzyn säännöt sallivat noppien heittämisen. false,
     * muuten.
     */
    public boolean voidaanHeittaa() {
        return this.heittojaJaljella > 0 && !this.valitutNopat.isEmpty();
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
     * Vuorossa oleva pelaaja vaihdetaan seuraavaan. Hänelle sallitaan kolme
     * heittoa.
     */
    public void seuraavaVuoro() {
        this.heitaNopat();
        if (!this.getPelaajat().isEmpty()) {
            this.vuoroNumero = (this.vuoroNumero + 1) % this.getPelaajat().size();
        }
        if (this.vuoroNumero == 0) {
            this.seuraavaKierros();
        }
        this.heittojaJaljella = 3;
        this.heitaNopat();
    }
    
    private void seuraavaKierros() {
        this.kierros++;
    }

    /**
     * Kertoo, onko peli päättynyt.
     *
     * @return true, jos peli on päättynyt false, muuten
     */
    public boolean peliLoppu() {
        return this.kierros > this.kierroksienKokonaismaara;
    }

    /**
     * Haetaan vuorossa oleva pelaaja.
     *
     * @return null, jos pelaajia ei ole. Vuoronumeron määräämä pelaaja, muuten.
     */
    public Pelaaja vuorossaOlevaPelaaja() {
        if (this.getPelaajat().isEmpty()) {
            return null;
        }
        return this.getPelaajat().get(this.getVuoroNumero());
    }

    /**
     * Lisää pelaajan peliin.
     *
     * @param nimi Lisättävän pelaajan nimi.
     */
    public boolean lisaaPelaaja(String nimi) {
        Pelaaja lisattava = new Pelaaja(nimi);
        boolean onnistui = false;
        if (!this.getPelaajat().contains(lisattava)) {
            this.getPelaajat().add(lisattava);
            onnistui = true;
        }
        onnistui = this.getPistelista().lisaaPelaaja(lisattava);
        return onnistui;
    }

    /**
     * Lisää pisteet vuorossa olevalle pelaajalle annetulle kierrokselle.
     *
     * @param kierrosNimi haluttu yatzy-kierros
     * @return true, jos pisteiden lisääminen onnistui false, muuten
     */
    public boolean lisaaPisteet(String kierrosNimi) {
        int pisteet = this.saannot
                .pisteyta(kierrosNimi, this.getNopat());
        boolean onnistui = this.getPistelista()
                .lisaaPisteet(this.vuorossaOlevaPelaaja(), kierrosNimi, pisteet);
        if (onnistui) {
            this.seuraavaVuoro();
        }
        return onnistui;
    }

    /**
     * Palauttaa pelin noppien nykyiset pisteluvut kokonaislukuina.
     *
     * @return noppien pisteluvut kokonaislukulistana
     */
    public List<Integer> nykyisetPisteluvut() {
        return this.getNopat()
                .stream()
                .map(Noppa::getPisteluku)
                .collect(Collectors.toList());
    }

    /**
     * Heitetään ne nopat, jotka oliomuuttuja määrää heitettäviksi. Vähennetään
     * vuoron jäljellä olevien heittojen määrää yhdellä.
     */
    public void heitaNopat() {
        if (this.voidaanHeittaa()) {
            for (Noppa noppa : this.getValitutNopat()) {
                noppa.heita();
            }
            this.getValitutNopat().clear();
            this.heittojaJaljella--;
        }
    }

    public List<Noppa> getNopat() {
        return nopat;
    }
}
