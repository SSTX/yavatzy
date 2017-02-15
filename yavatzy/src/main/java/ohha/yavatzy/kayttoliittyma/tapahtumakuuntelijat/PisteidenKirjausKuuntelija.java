/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Tapahtumakuuntelija, joka huolehtii pisteiden kirjaamisesta pistelistaan.
 *
 * @author ttiira
 */
public class PisteidenKirjausKuuntelija implements ActionListener {

    private Peli peli;
    private JButton heitaNopatNappi;
    private JComboBox kierrosValintaLaatikko;
    private JTextArea pisteKirjausLaatikko;
    /**
     * Luodaan tapahtumakuuntelija.
     *
     * @param peli peli, johon tämä kuuntelija liittyy
     * @param heitaNopatNappi käyttöliittymän nappi, jonka painaminen heittää valitut nopat. Kun pisteet kirjataan, nappi muuttuu enabled-tilaan
     * @param kierrosValintaLaatikko valintalaatikko, josta käyttäjä valitsee tallennettavan kierroksen nimen
     * @param pisteKirjausLaatikko tekstikenttä, jossa näytetään kertyneet pisteet
     */
    public PisteidenKirjausKuuntelija(Peli peli, JButton heitaNopatNappi, JComboBox kierrosValintaLaatikko,
            JTextArea pisteKirjausLaatikko) {
        this.peli = peli;
        this.heitaNopatNappi = heitaNopatNappi;
        this.kierrosValintaLaatikko = kierrosValintaLaatikko;
        this.pisteKirjausLaatikko = pisteKirjausLaatikko;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.heitaNopatNappi.setEnabled(true);
        this.peli.lisaaPisteet(this.kierrosValintaLaatikko.getSelectedItem().toString());
        this.peli.seuraavaVuoro();
        this.peli.getValitutNopat().addAll(this.peli.getNopat());
        //heitetään noppia kerran, että saadaan edellisen pelaajan pisteluvut pois
        this.heitaNopatNappi.doClick();
        this.pisteKirjausLaatikko.setText(this.peli.getPistelista().toString());
    }

}
