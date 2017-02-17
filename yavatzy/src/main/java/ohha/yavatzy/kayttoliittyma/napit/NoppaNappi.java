/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.napit;

import javax.swing.JToggleButton;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.sovelluslogiikka.Noppa;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Jatkettu JToggleButton, joka tietää mihin yatzy-peliin se kuuluu ja mikä noppa siihen liittyy.
 * @author ttiira
 */
public class NoppaNappi extends JToggleButton implements Paivitettava {
    private Noppa noppa;
    private Peli peli;
    
    /**
     * Luo NoppaNappi-olion.
     * @param nimi Napin nimi, annetaan JToggleButtonin konstruktorille
     * @param noppa Nappiin liittyvä noppa-olio
     * @param peli Peliolio, johon nappi liittyy
     */
    public NoppaNappi(String nimi, Noppa noppa, Peli peli) {
        super(nimi);
        this.noppa = noppa;
        this.peli = peli;
    }
    
    @Override
    public void paivita() {
        this.setText(this.noppa.toString());
        this.setSelected(this.peli.getValitutNopat().contains(this.noppa));
    }
    
    public Noppa getNoppa() {
        return this.noppa;
    }
}
