package ohha.yavatzy.kayttoliittyma;

import java.awt.event.ActionListener;
import javafx.event.ActionEvent;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanHeittoKuuntelija implements ActionListener {
    private Peli peli;
    public NopanHeittoKuuntelija(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.heitaNopat();
    }
}
