package ohha.yavatzy.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import ohha.yavatzy.sovelluslogiikka.Noppa;
import ohha.yavatzy.sovelluslogiikka.Peli;
import ohha.yavatzy.KierrosNimet;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.PisteidenKirjausKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.NopanValintaKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.NopanHeittoKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.PelaajanLisaysKuuntelija;

/**
 * Graafinen käyttöliittymä yatzy-pelille. Luo ikkunan ja käyttöliittymän
 * komponentit.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Peli peli;
    private int pelaajienMaara;

    /**
     * Konstruoi Kayttoliittyma-olion.
     *
     * @param peli peli-olio, jota tämä käyttöliittymä esittää
     */
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
        this.pelaajienMaara = 2;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(new Dimension(1280, 720));
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
        this.luoPisteLista();
        this.luoNopanHeittoPaneeli();
    }

    private GridBagConstraints luoNoppaNappiRajat() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridy = 0;
        return c;
    }

    private GridBagConstraints luoToisenRivinRajat() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        return c;
    }

    private GridBagConstraints luoPisteListausLaatikonRajat() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.gridwidth = 6;
        c.gridheight = 6;
        c.ipady = 50;
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    private List<JToggleButton> luoNoppaNapit(Container container) {
        List<JToggleButton> noppaNapit = new ArrayList<>();
        for (Noppa noppa : this.getPeli().getNopat()) {
            JToggleButton noppaNappi = new JToggleButton(noppa.toString());
            noppaNappi.addActionListener(new NopanValintaKuuntelija(this.peli, noppaNappi, noppa));
            noppaNapit.add(noppaNappi);
        }
        return noppaNapit;
    }

    private JButton luoHeitaNopatNappi(Container container, List<JToggleButton> noppaNapit) {
        JButton heitaNopatNappi = new JButton("Heitä nopat");
        heitaNopatNappi.addActionListener(new NopanHeittoKuuntelija(this.peli, heitaNopatNappi, noppaNapit));
        return heitaNopatNappi;
    }

    private JButton luoKirjaaPisteetNappi(JButton heitaNopatNappi,
            JComboBox kierrosValintaLaatikko, JTextArea pisteListausLaatikko) {
        JButton kirjaaPisteetNappi = new JButton("Kirjaa pisteet");
        kirjaaPisteetNappi.addActionListener(new PisteidenKirjausKuuntelija(this.peli,
                heitaNopatNappi, kierrosValintaLaatikko, pisteListausLaatikko));
        return kirjaaPisteetNappi;
    }

    private void luoPisteLista() {
        JPanel paneeli = new JPanel();
        paneeli.setPreferredSize(new Dimension(800, 400));
        paneeli.setLayout(new GridBagLayout());
        GridBagConstraints pisteListaRajat = new GridBagConstraints();
        pisteListaRajat.fill = GridBagConstraints.BOTH;
        pisteListaRajat.gridy = 1;

        //kierrosten nimet vasempaan sarakkeeseen
        for (int i = 0; i < KierrosNimet.kierrosNimet().length; i++) {
            JLabel nappi = new JLabel(KierrosNimet.kierrosNimet()[i]);
            paneeli.add(nappi, pisteListaRajat);
            pisteListaRajat.gridy++;
        }
        paneeli.add(new JLabel("Yhteensä"), pisteListaRajat);
        //pelaajien nimet yläriviin
        for (int i = 0; i < this.pelaajienMaara; i++) {
            pisteListaRajat.gridy = 0;
            pisteListaRajat.gridx = i + 1;
            JTextField pelaajaNimi = new JTextField("", 20);
            pelaajaNimi.addActionListener(new PelaajanLisaysKuuntelija(pelaajaNimi, this.peli));
            paneeli.add(pelaajaNimi, pisteListaRajat);
        }
        //pistetaulukko
        for (int j = 1; j <= this.pelaajienMaara; j++) {
            pisteListaRajat.gridx = j;
            for (int i = 0; i < KierrosNimet.kierrosNimet().length; i++) {
                pisteListaRajat.gridy = i + 1;
                PisteListaNappi nappi = new PisteListaNappi("", KierrosNimet.kierrosNimet()[i], j-1);
                paneeli.add(nappi, pisteListaRajat);
            }
        }
        frame.getContentPane().add(paneeli);
    }

    private void luoNopanHeittoPaneeli() {
        JPanel paneeli = new JPanel();
        List<JToggleButton> noppaNapit = this.luoNoppaNapit(paneeli);
        JButton heitaNopatNappi = this.luoHeitaNopatNappi(paneeli, noppaNapit);
        JComboBox kierrosValintaLaatikko = new JComboBox(KierrosNimet.kierrosNimet());
        JTextArea pisteListausLaatikko = new JTextArea();
        JButton kirjaaPisteetNappi = this.luoKirjaaPisteetNappi(heitaNopatNappi, kierrosValintaLaatikko, pisteListausLaatikko);
        GridBagConstraints noppaNappiRajat = this.luoNoppaNappiRajat();
        GridBagConstraints toisenRivinRajat = this.luoToisenRivinRajat();
        GridBagConstraints pisteListausLaatikonRajat = this.luoPisteListausLaatikonRajat();

        noppaNapit.stream().forEach((nappi) -> paneeli.add(nappi, noppaNappiRajat));
        paneeli.add(heitaNopatNappi);
        paneeli.add(kierrosValintaLaatikko, toisenRivinRajat);
        paneeli.add(kirjaaPisteetNappi, toisenRivinRajat);
        paneeli.add(pisteListausLaatikko, pisteListausLaatikonRajat);

        heitaNopatNappi.doClick();
        frame.getContentPane().add(paneeli);
    }
}
