/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 *
 * @author ttiira
 */
public class PelaajanLisaysKuuntelija implements ActionListener {

    private JTextField nimiKentta;
    private Peli peli;

    public PelaajanLisaysKuuntelija(JTextField nimiKentta, Peli peli) {
        this.nimiKentta = nimiKentta;
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.peli.getKierros() == 1 
                && this.peli.lisaaPelaaja(this.nimiKentta.getText())) {
            this.nimiKentta.setEditable(false);
        }
    }
}
