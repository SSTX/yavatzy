package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ohha.yavatzy.kayttoliittyma.Paivitettava;
import ohha.yavatzy.kayttoliittyma.napit.NoppaNappi;

import ohha.yavatzy.sovelluslogiikka.Peli;
/**
 * ActionListener-rajapinnan toteuttava luokka, jonka vastuulla on merkitä noppia heitettäväksi peli-oliossa.
 */
public class NopanValintaKuuntelija implements ActionListener {

    private Peli peli;
    private Paivitettava kayttoliittyma;

    /**
     * Luodaan tapahtumakuuntelija.
     * @param peli peli, johon kuuntelijan hoitamat nopat kuuluvat
     * @param kayttoliittyma käyttöliittymä, jonka tapahtumia tämä luokka kuuntelee
     */
    public NopanValintaKuuntelija(Peli peli, Paivitettava kayttoliittyma) {
        this.peli = peli;
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        NoppaNappi noppaNappi = (NoppaNappi) ae.getSource();
        if (!noppaNappi.isSelected()) {
            this.peli.poistaNopanValinta(noppaNappi.getNoppa());
        } else {
            this.peli.valitseNoppa(noppaNappi.getNoppa());
        }
        this.kayttoliittyma.paivita();
    }
}
