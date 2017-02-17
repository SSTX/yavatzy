/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.napit;

import javax.swing.JButton;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Jatkettu JButton, joka tietää mihin yatzy-peliin se kuuluu.
 * @author ttiira
 */
public class PelinAloitusNappi extends JButton implements Paivitettava{
    private Peli peli;
    /**
     * Luo PelinAloitusNappi-olion
     * @param nimi napin nimi, annetaan JButtonin konstruktorille
     * @param peli peli, jonka tämän napin painaminen aloittaa
     */
    public PelinAloitusNappi(String nimi, Peli peli) {
        super(nimi);
        this.peli = peli;
    }
    
    
    @Override
    public void paivita() {
        this.setEnabled(!this.peli.getPelaajat().isEmpty());
    }
    
}
