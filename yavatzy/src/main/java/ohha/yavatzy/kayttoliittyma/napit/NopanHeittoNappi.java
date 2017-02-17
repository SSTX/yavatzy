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
 *
 * @author ttiira
 */
public class NopanHeittoNappi extends JButton implements Paivitettava {
    private Peli peli;
    
    public NopanHeittoNappi(String nimi, Peli peli) {
        super(nimi);
        this.peli = peli;
    }
    @Override
    public void paivita() {
        this.setEnabled(this.peli.voidaanHeittaa());
    }
    
}
