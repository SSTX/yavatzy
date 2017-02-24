/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.Arrays;
import ohha.yavatzy.sovelluslogiikka.domain.Pelaaja;
import java.util.HashMap;
import java.util.Map;

import ohha.yavatzy.KierrosNimet;

/**
 * Luokka tallentaa monen pelaajan pisteet monelta kierrokselta,
 * sekä pelaajan kokonaispistemäärän ja bonuksen.
 */
public class Pistelista {

    // Map<Pelaaja, Map<Kierrosnimi, Pisteet>>
    private Map<Pelaaja, Map<String, Integer>> pistelista;
    private int bonusPisteet;
    private int bonusRaja;

    /**
     * Luodaan pistelista.
     */
    public Pistelista() {
        this.pistelista = new HashMap<>();
        this.bonusPisteet = 50;
        this.bonusRaja = 63;
    }

    public Map<Pelaaja, Map<String, Integer>> getPistelista() {
        return this.pistelista;
    }

    /**
     * Lisää pelaajan pistelistalle.
     *
     * @param pelaaja lisättävä pelaaja
     * @return true, jos pelaajan lisääminen onnistui, false muuten
     */
    public boolean lisaaPelaaja(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            this.getPistelista().put(pelaaja, new HashMap<>());
            //alustetaan yhteispistemäärä
            this.getPistelista().get(pelaaja).put("yhteensä", 0);
            return true;
        }
        return false;
    }

    /**
     * Lisää listaan pisteet tietylle pelaajalle tietylle kierrokselle.
     *
     * @param pelaaja pisteiden omistaja
     * @param kierrosNimi pisteytetty kierros
     * @param pisteet pisteiden määrä
     * @return true, jos pisteiden lisääminen onnistui false, muuten
     */
    public boolean lisaaPisteet(Pelaaja pelaaja, String kierrosNimi, int pisteet) {
        Map<String, Integer> pelaajanPisteet = this.getPistelista().get(pelaaja);
        if (pelaajanPisteet != null && !pelaajanPisteet.containsKey(kierrosNimi)) {
            pelaajanPisteet.put(kierrosNimi, pisteet);
            if (this.bonus(pelaaja)) {
                pelaajanPisteet.put("bonus", this.bonusPisteet);
            }
            pelaajanPisteet.put("yhteensä", this.pelaajanKokonaisPisteet(pelaaja));
            return true;
        }
        return false;
    }

    /**
     * Palauttaa tietyn pelaajan pisteet tietyltä kierrokselta.
     *
     * @param pelaaja pisteiden omistaja
     * @param kierrosNimi pisteytetty kierros
     * @return pelaajan kierroksen pistemäärä
     */
    public Integer pelaajanPisteetKierrokselta(Pelaaja pelaaja, String kierrosNimi) {
        if (this.getPistelista().containsKey(pelaaja)) {
            return this.getPistelista().get(pelaaja).get(kierrosNimi);
        }
        return null;
    }

    private int pelaajanKokonaisPisteet(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            return 0;
        }
        int summa = 0;
        for (String kierros : KierrosNimet.kierrosNimet()) {
            Integer pisteet = this.pelaajanPisteetKierrokselta(pelaaja, kierros);
            if (pisteet != null) {
                summa += pisteet;
            }
        }
        return summa;
    }
    
    private boolean bonus(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            return false;
        }

        int huomioitavatPisteet = 0;
        for (String kierros : KierrosNimet.bonusKierrosNimet()) {
            if (this.getPistelista().get(pelaaja).containsKey(kierros)) {
                huomioitavatPisteet += this.getPistelista().get(pelaaja).get(kierros);
            }
        }
        return huomioitavatPisteet >= this.bonusRaja;
    }
    
    /**
     * Kertoo kuinka monta pistettä lisää pelaaja tarvitsee saadakseen bonuksen.
     * @param pelaaja Pelaaja, jolle tarvittava pistemäärä lasketaan.
     * @return Pelaajan tarvitsemien pisteiden määrä. Bonuskierrosten pisteiden erotus 63:sta.
     */
    public int matkaaBonukseen(Pelaaja pelaaja) {
        int tavoite = this.bonusRaja;
        for (String kierros : KierrosNimet.bonusKierrosNimet()) {
            Integer pisteet = this.pelaajanPisteetKierrokselta(pelaaja, kierros);
            if (pisteet != null) {
                tavoite -= pisteet;
            }
        }
        return tavoite;
    }
}
