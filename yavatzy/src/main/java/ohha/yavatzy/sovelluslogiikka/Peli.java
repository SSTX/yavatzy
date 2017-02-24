/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import ohha.yavatzy.sovelluslogiikka.domain.Noppa;
import ohha.yavatzy.sovelluslogiikka.domain.Pelaaja;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pelin logiikka. Pitää kirjaa vuoroista, kierrosnumerosta, pelaajista. Tietää
 * myös noppien heittämiseen liittyvät säännöt.
 */
public class Peli {

    private Pistelista pistelista;
    private Pisteytyssaannot saannot;
    private VuoroKirjanpito vuoroKirjanpito;
    private NopanHeittaja nopanHeittaja;

    /**
     * Luodaan peli annetuilla nopilla.
     * @param nopat pelin käyttämät nopat
     * @param pelaajat peliin kuuluvat pelaajat
     */
    public Peli(List<Noppa> nopat, List<Pelaaja> pelaajat) {
        this.pistelista = new Pistelista();
        this.saannot = new Pisteytyssaannot();
        this.vuoroKirjanpito = new VuoroKirjanpito(15);
        this.nopanHeittaja = new NopanHeittaja(nopat);
        pelaajat.stream().forEach((pelaaja) -> this.lisaaPelaaja(pelaaja.getNimi()));
    }

    /**
     * Luodaan peli oletusnopilla ja ilman pelaajia.
     */
    public Peli() {
        this(null, new ArrayList<>());
    }

    /**
     * Luodaan peli ilman pelaajia.
     * @param nopat pelin käyttämät nopat
     */
    public Peli(List<Noppa> nopat) {
        this(nopat, new ArrayList<>());
    }


    public List<Noppa> getNopat() {
        return this.nopanHeittaja.getNopat();
    }

    public List<Pelaaja> getPelaajat() {
        return this.vuoroKirjanpito.getPelaajat();
    }

    public Pistelista getPistelista() {
        return pistelista;
    }

    public List<Noppa> getValitutNopat() {
        return this.nopanHeittaja.getValitutNopat();
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
     * Vuorossa oleva pelaaja vaihdetaan seuraavaan. 
     */
    public void seuraavaVuoro() {
        this.vuoroKirjanpito.seuraavaVuoro();
        this.nopanHeittaja.seuraavaVuoro();
    }

    /**
     * Kertoo, onko peli päättynyt.
     *
     * @return true, jos peli on päättynyt false, muuten
     */
    public boolean peliLoppu() {
        return this.vuoroKirjanpito.kierroksetKayty();
    }

    /**
     * Haetaan vuorossa oleva pelaaja.
     *
     * @return null, jos pelaajia ei ole. Vuoronumeron määräämä pelaaja, muuten.
     */
    public Pelaaja vuorossaOlevaPelaaja() {
        return this.vuoroKirjanpito.vuorossaOlevaPelaaja();
    }

    /**
     * Lisää pelaajan peliin.
     * @param nimi Lisättävän pelaajan nimi.
     * @return true, jos pelaajan lisäys onnistui. false muuten.
     */
    public boolean lisaaPelaaja(String nimi) {
        Pelaaja lisattava = new Pelaaja(nimi);
        return this.vuoroKirjanpito.lisaaPelaaja(lisattava) && this.pistelista.lisaaPelaaja(lisattava);
    }

    /**
     * Lisää pisteet vuorossa olevalle pelaajalle annetulle kierrokselle.
     *
     * @param kierrosNimi haluttu yatzy-kierros
     * @return true, jos pisteiden lisääminen onnistui false, muuten
     */
    public boolean lisaaPisteet(String kierrosNimi) {
        int pisteet = this.saannot.pisteyta(kierrosNimi, this.getNopat());
        boolean onnistui = this.getPistelista().lisaaPisteet(this.vuorossaOlevaPelaaja(), kierrosNimi, pisteet);
        if (onnistui) {
            this.seuraavaVuoro();
        }
        return onnistui;
    }

    /**
     * Heitetään käyttöliittymästä valitut nopat.
     */
    public void heitaNopat() {
        this.nopanHeittaja.heitaValitutNopat();
    }

    /**
     * Kertoo, voiko vuorossa oleva pelaaja heittää vielä noppia.
     *
     * @return true, jos yatzyn säännöt sallivat noppien heittämisen. false,
     * muuten.
     */
    public boolean heittojaJaljella() {
        return this.nopanHeittaja.heittojaJaljella();
    }
    
    /**
     * Kertoo, onko noppia valittu heitettäväksi vai ei.
     * @return true jos noppia on valittu, false muuten
     */
    public boolean noppiaValittu() {
        return this.nopanHeittaja.noppiaValittu();
    }
}
