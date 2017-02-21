/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * ActionListener-rajapinnan toteuttava luokka, jonka vastuulla on lisätä peli-olioon pelaajia.
 * @author ttiira
 */
public class PelaajanLisaysKuuntelija implements ActionListener {

    private Peli peli;
    private Paivitettava kayttoliittyma;
    
    /**
     * Luodaan tapahtumakuuntelija.
     * @param peli peli-olio, johon tämä luokka lisää pelaajia
     * @param kayttoliittyma käyttöliittymä, jonka tapahtumia tämä luokka kuuntelee
     */
    public PelaajanLisaysKuuntelija(Peli peli, Paivitettava kayttoliittyma) {
        this.peli = peli;
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JTextField nimiKentta = (JTextField) ae.getSource();
        boolean onnistui = this.peli.lisaaPelaaja(nimiKentta.getText());
        nimiKentta.setEditable(!onnistui);
        if (onnistui) {
            nimiKentta.removeActionListener(this);
        }
        kayttoliittyma.paivita();
    }
}
