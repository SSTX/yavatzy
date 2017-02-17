/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.napit;

import javax.swing.JButton;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;
import ohha.yavatzy.sovelluslogiikka.Peli;
import ohha.yavatzy.sovelluslogiikka.Pisteytyssaannot;

/**
 * Lisätty JButton, joka tietää, mikä yatzy-kierros ja pelaaja siihen liittyy. Tietää myös, onko siihen tallennettu jonkin kierroksen pistemäärä.
 *
 * @author ttiira
 */
public class PisteListaNappi extends JButton implements Paivitettava {

    private final String kierros;
    private Pelaaja pelaaja;
    private Peli peli;
    private Pisteytyssaannot saannot;
    private boolean taytetty;

    /**
     * Luo PisteListaNappi-olion.
     * @param nimi napin nimi, annetaan yläluokan konstruktorille
     * @param kierros yatzy-kierros, johon tämä nappi liittyy
     * @param pelaaja pelaaja, johon tämä nappi liittyy
     * @param peli peli, johon tämä nappi liittyy
     */
    public PisteListaNappi(String nimi, String kierros, Pelaaja pelaaja, Peli peli) {
        super(nimi);
        this.kierros = kierros;
        this.pelaaja = pelaaja;
        this.peli = peli;
        this.taytetty = false;
        this.saannot = new Pisteytyssaannot();
    }

    @Override
    public void paivita() {
        Integer pisteet = this.peli.getPistelista().pelaajanPisteetKierrokselta(pelaaja, kierros);
        if (pisteet == null) {
            this.setText(" ");
        } else {
            this.setText(Integer.toString(pisteet));
        }
        this.setEnabled(!taytetty && this.pelaaja.equals(this.peli.vuorossaOlevaPelaaja()));
    }

    public String getKierros() {
        return kierros;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    /**
     * Asettaa napin tilaksi täytetty. Tämän jälkeen nappia ei voi painaa.
     */
    public void tayta() {
        this.taytetty = true;
    }

}
