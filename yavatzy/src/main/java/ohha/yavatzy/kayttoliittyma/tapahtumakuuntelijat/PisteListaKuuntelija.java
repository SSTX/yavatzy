/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.kayttoliittyma.napit.PisteListaNappi;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 *
 * @author ttiira
 */
public class PisteListaKuuntelija implements ActionListener {

    private Peli peli;
    private Paivitettava kayttoliittyma;
    
    public PisteListaKuuntelija(Peli peli, Paivitettava kayttoliittyma) {
        this.peli = peli;
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        PisteListaNappi laukaisija = (PisteListaNappi)ae.getSource();
        laukaisija.tayta();
        this.peli.lisaaPisteet(laukaisija.getKierros());
        this.kayttoliittyma.paivita();
    }
}
