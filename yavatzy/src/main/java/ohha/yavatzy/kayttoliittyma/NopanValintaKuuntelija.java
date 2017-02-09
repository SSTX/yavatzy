package ohha.yavatzy.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import ohha.yavatzy.sovelluslogiikka.Peli;
public class NopanValintaKuuntelija implements ActionListener {
    private Peli peli;
    private JButton noppaNappi;
    public NopanValintaKuuntelija(Peli peli, JButton noppaNappi) {
        this.peli = peli;
        this.noppaNappi = noppaNappi;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
