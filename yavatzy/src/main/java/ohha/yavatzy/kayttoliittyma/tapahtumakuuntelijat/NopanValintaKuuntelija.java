package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import ohha.yavatzy.sovelluslogiikka.Noppa;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanValintaKuuntelija implements ActionListener {

    private Peli peli;
    private JToggleButton noppaNappi;
    private Noppa noppa;

    /**
     * Luodaan tapahtumakuuntelija.
     * @param peli peli, johon kuuntelijan hoitamat nopat kuuluvat
     * @param noppaNappi nappi, johon tämä kuuntelija liittyy
     * @param noppa noppa, jota tämä kuuntelija käsittelee
     */
    public NopanValintaKuuntelija(Peli peli, JToggleButton noppaNappi, Noppa noppa) {
        this.peli = peli;
        this.noppaNappi = noppaNappi;
        this.noppa = noppa;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!this.noppaNappi.isSelected()) {
            this.peli.poistaNopanValinta(this.noppa);
        } else {
            this.peli.valitseNoppa(this.noppa);
        }
    }
}
