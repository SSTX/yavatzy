package ohha.yavatzy.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanHeittoKuuntelija implements ActionListener {
    private Peli peli;
    public NopanHeittoKuuntelija(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.heitaNopat(this.peli.getValitutNopat());
    }
}
