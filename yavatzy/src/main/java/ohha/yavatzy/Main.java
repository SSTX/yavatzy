/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

import javax.swing.SwingUtilities;
import ohha.yavatzy.kayttoliittyma.Kayttoliittyma;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Pääohjelma. Käynnistää yatzy-pelin.
 */
public class Main {

    /**
     * main-metodi. Käynnistää ohjelman.
     *
     * @param args Ohjelmalle ulkopuolelta annetut argumentit
     */
    public static void main(String[] args) {
        Peli peli = new Peli();
        //peli.lisaaPelaaja("Jalmari");
        Kayttoliittyma kayttis = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kayttis);
    }
}
