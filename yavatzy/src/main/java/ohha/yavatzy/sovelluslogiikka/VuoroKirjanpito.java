package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import ohha.yavatzy.sovelluslogiikka.domain.Pelaaja;

/**
 * Luokka pitää kirjaa yatzy-pelin pelaajien vuoroista.
 * @author ttiira
 */
public class VuoroKirjanpito {

    private int vuoroNumero;
    private int kierrosNumero;
    private int kierrostenKokonaismaara;
    private List<Pelaaja> pelaajat;

    /**
     * Luodaan vuorokirjanpito.
     * @param kierrostenKokonaismaara kuinka monta kierrosta yatzy-pelissä on (15)
     */
    public VuoroKirjanpito(int kierrostenKokonaismaara) {
        this.kierrostenKokonaismaara = kierrostenKokonaismaara;
        this.vuoroNumero = 0;
        this.kierrosNumero = 0;
        this.pelaajat = new ArrayList<>();
    }

    public int getVuoroNumero() {
        return vuoroNumero;
    }

    public int getKierrosNumero() {
        return kierrosNumero;
    }

    public int getKierrostenKokonaismaara() {
        return kierrostenKokonaismaara;
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    /**
     * Kertoo, jatkuuko peli vai onko kaikki kierrokset jo käyty.
     * @return true, jos kaikki kierrokset on käyty, false muuten
     */
    public boolean kierroksetKayty() {
        return this.kierrosNumero >= this.kierrostenKokonaismaara;
    }
    
    /**
     * Kertoo kuinka monta pelaajaa pelissä on.
     * @return pelaajien lukumäärä
     */
    public int pelaajienMaara() {
        return this.getPelaajat().size();
    }

    /**
     * Vaihtaa vuorossa olevan pelaajan järjestyksessä seuraavaan. Viimeisestä pelaajasta palataan ensimmäiseen, ja siirrytään seuraavaan kierrokseen.
     */
    public void seuraavaVuoro() {
        this.vuoroNumero++;
        this.vuoroNumero %= this.pelaajienMaara();
        if (this.vuoroNumero == 0) {
            this.seuraavaKierros();
        }
    }
    
    /**
     * Kaikki pelaajat ovat heittäneet, siirrytään seuraavaan kierrokseen.
     */
    public void seuraavaKierros() {
        this.kierrosNumero++;
    }

    /**
     * Palautaa pelaaja-olion, joka on yatzy-pelin heittovuorossa.
     * @return pelaaja, jonka vuoro on heittää
     */
    public Pelaaja vuorossaOlevaPelaaja() {
        return this.getPelaajat().get(this.getVuoroNumero());
    }

    /**
     * Lisää pelaajan kirjanpitoon.
     * @param pelaaja lisättävä pelaaja
     * @return true jos lisääminen onnistui, false muuten
     */
    public boolean lisaaPelaaja(Pelaaja pelaaja) {
        if (!this.getPelaajat().contains(pelaaja)) {
            this.getPelaajat().add(pelaaja);
            return true;
        }
        return false;
    }

}
