package ohha.yavatzy.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanValintaKuuntelija implements ActionListener {
    private Peli peli;
    private JButton noppaNappi;
    private int indeksi;
    private boolean valittu;

    public NopanValintaKuuntelija(Peli peli, JButton noppaNappi, int indeksi) {
        this.peli = peli;
        this.noppaNappi = noppaNappi;
        this.indeksi = indeksi;
        this.valittu = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.valittu) {
            this.peli.poistaNopanValinta(this.indeksi);
            this.valittu = false;
        } else {
            this.peli.valitseNoppa(this.indeksi);
            this.valittu = true;
        }
    }
}
