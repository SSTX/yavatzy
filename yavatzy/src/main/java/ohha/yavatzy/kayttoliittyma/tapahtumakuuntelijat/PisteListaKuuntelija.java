/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import ohha.yavatzy.kayttoliittyma.PisteListaNappi;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 *
 * @author ttiira
 */
public class PisteListaKuuntelija implements ActionListener {

    private Peli peli;
    private JButton heitaNopatNappi;
    private List<PisteListaNappi> napit;
    private PisteListaNappi laukaisija;

    public PisteListaKuuntelija(Peli peli, JButton heitaNopatNappi, PisteListaNappi laukaisija, List<PisteListaNappi> napit) {
        this.peli = peli;
        this.heitaNopatNappi = heitaNopatNappi;
        this.napit = napit;
        this.laukaisija = laukaisija;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.peli.lisaaPisteet(this.laukaisija.getKierros());
        napit.stream().forEach((nappi) -> {
            if (!this.peli.getPelaajat().get(nappi.getId()).equals(this.peli.vuorossaOlevaPelaaja())) {
                nappi.setEnabled(false);
            } else if (this.peli.getPistelista().getPistelista().containsKey(nappi.getKierros()))
        });

    }
}
