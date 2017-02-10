package ohha.yavatzy.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Peli peli;

    public Kayttoliittyma() {
        this.peli = new Peli(5,6);
    }

    @Override
    public void run() {
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(new Dimension(400, 600));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public Peli getPeli() {
        return this.peli;
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridBagLayout());
        JButton heitaNopatNappi = new JButton("Heitä nopat");
        container.add(heitaNopatNappi);
        heitaNopatNappi.addActionListener(new NopanHeittoKuuntelija(this.peli));
        for (int i = 0; i < this.getPeli().getNoppienMaara(); i++) {
            JButton noppaNappi = new JButton(Integer.toString(i));
            container.add(noppaNappi);
            noppaNappi.addActionListener(new NopanValintaKuuntelija(this.peli, noppaNappi, i));
        }
    }
}
