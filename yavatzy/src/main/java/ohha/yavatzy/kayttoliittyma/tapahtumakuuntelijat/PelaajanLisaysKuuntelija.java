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
 *
 * @author ttiira
 */
public class PelaajanLisaysKuuntelija implements ActionListener {

    private Peli peli;
    private Paivitettava kayttoliittyma;
    
    public PelaajanLisaysKuuntelija(Peli peli, Paivitettava kayttoliittyma) {
        this.peli = peli;
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JTextField nimiKentta = (JTextField)ae.getSource();
        boolean onnistui = this.peli.lisaaPelaaja(nimiKentta.getText());
        nimiKentta.setEditable(!onnistui);
        kayttoliittyma.paivita();
    }
}
