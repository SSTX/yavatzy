package ohha.yavatzy.kayttoliittyma;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ohha.yavatzu.sovelluslogiikka.Peli;

public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    private Peli peli;

    public Kayttoliittyma() {
        this.peli = new Peli
    }

    @Override
    public void run() {
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(400, 600);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JButton heitaNopatNappi = new JButton("Heitä nopat");
        container.add(heitaNopatNappi);
        heitaNopatNappi.addActionListener(new NopanHeittoKuuntelija(this.peli));
    }
}
