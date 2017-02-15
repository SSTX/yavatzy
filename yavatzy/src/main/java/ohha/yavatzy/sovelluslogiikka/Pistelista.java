/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ohha.yavatzy.KierrosNimet;

/**
 * Luokka tallentaa monen pelaajan pisteet monelta kierrokselta. Tietää myös
 * yatzyn bonus-säännön ja osaa laskea pelaajan kokonaispisteet.
 */
public class Pistelista {

    private Map<Pelaaja, Map<String, Integer>> pistelista;
    private int bonusPisteet;
    private int bonusRaja;
    private String[] bonusKierrokset;

    /**
     * Luodaan pistelista.
     */
    public Pistelista() {
        this.pistelista = new HashMap<>();
        this.bonusPisteet = 50;
        this.bonusRaja = 63;
        this.bonusKierrokset = new String[]{"ykköset",
            "kakkoset",
            "kolmoset",
            "neloset",
            "vitoset",
            "kutoset",
        };
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
    public int pelaajanPisteetKierrokselta(Pelaaja pelaaja, String kierrosNimi) {
        if (this.getPistelista().containsKey(pelaaja)
                && this.getPistelista().get(pelaaja).containsKey(kierrosNimi)) {
            return this.getPistelista().get(pelaaja).get(kierrosNimi);
        }
        return 0;
    }

    /**
     * Palauttaa pelaajan senhetkiset kokonaispisteet.
     *
     * @param pelaaja pisteiden omistaja
     * @return Pisteiden kokonaismäärä, bonus mukaanluettuna
     */
    public int pelaajanKokonaisPisteet(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            return 0;
        }
        return this.getPistelista().get(pelaaja).values()
                .stream().reduce(0, (a, b) -> a + b);
    }

    /**
     * Kertoo, onko tietty pelaaja saavuttanut bonuksen.
     *
     * @param pelaaja tarkasteltava pelaaja
     * @return true, jos pelaaja on saavuttanut bonuksen, false muuten
     */
    public boolean bonus(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            return false;
        }

        int huomioitavatPisteet = 0;
        for (String kierros : this.bonusKierrokset) {
            if (this.getPistelista().get(pelaaja).containsKey(kierros)) {
                huomioitavatPisteet += this.getPistelista().get(pelaaja).get(kierros);
            }
        }
        return huomioitavatPisteet >= this.bonusRaja;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Pelaaja pelaaja : this.getPistelista().keySet()) {
            builder.append(pelaaja);
            builder.append(": \n");
            for (String kierros : KierrosNimet.kierrosNimet()) {
                builder.append(kierros);
                builder.append(": ");
                builder.append(this.getPistelista().get(pelaaja).get(kierros));
                builder.append("\n");
            }
            builder.append("Yhteensä: ");
            builder.append(this.pelaajanKokonaisPisteet(pelaaja));
        }
        return builder.toString();
    }
}
