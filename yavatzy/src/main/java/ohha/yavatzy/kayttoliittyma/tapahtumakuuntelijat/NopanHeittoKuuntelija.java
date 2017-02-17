package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import ohha.yavatzy.kayttoliittyma.Paivitettava;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanHeittoKuuntelija implements ActionListener {

    private Peli peli;
    private Paivitettava kayttoliittyma;

    /**
     * Luodaan tapahtumakuuntelija.
     *
     * @param peli peli, johon heitettävät nopat kuuluvat
     * @param heitaNopatNappi nappi, johon tämä kuuntelija liittyy
     * @param nopanValintaNapit heitettävien noppien valintaan käytettävät
     * napit, kun nopat heitetään, poistetaan valinta kaikista
     */
    public NopanHeittoKuuntelija(Peli peli, Paivitettava kayttoliittyma) {
        this.peli = peli;
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.heitaNopat();
        this.kayttoliittyma.paivita();
    }
}
