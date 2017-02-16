/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma;

import javax.swing.JButton;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Lisätty JButton, joka tietää, mikä yatzy-kierros ja pelaaja siihen liittyy
 * @author ttiira
 */
public class PisteListaNappi extends JButton {
    private final String kierros;
    private final Pelaaja pelaaja;
    
    public PisteListaNappi(String kierros, Pelaaja pelaaja) {
        this.kierros = kierros;
        this.pelaaja = pelaaja;
    }

    public String getKierros() {
        return kierros;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }
    
    
}
