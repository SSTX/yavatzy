/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ohha.yavatzy.kayttoliittyma.Kayttoliittyma;
import ohha.yavatzy.kayttoliittyma.napit.PelinAloitusNappi;

/**
 *
 * @author ttiira
 */
public class PelinAloitusKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;

    public PelinAloitusKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.kayttoliittyma.aloitaPeli();
    }
    
}
