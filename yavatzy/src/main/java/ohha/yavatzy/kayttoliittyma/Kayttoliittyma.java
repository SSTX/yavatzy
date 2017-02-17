package ohha.yavatzy.kayttoliittyma;

import ohha.yavatzy.kayttoliittyma.napit.PisteListaNappi;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ohha.yavatzy.sovelluslogiikka.Noppa;
import ohha.yavatzy.sovelluslogiikka.Peli;
import ohha.yavatzy.KierrosNimet;
import ohha.yavatzy.kayttoliittyma.napit.NopanHeittoNappi;
import ohha.yavatzy.kayttoliittyma.napit.NoppaNappi;
import ohha.yavatzy.kayttoliittyma.napit.PelinAloitusNappi;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.NopanHeittoKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.NopanValintaKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.PelaajanLisaysKuuntelija;
import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.PelinAloitusKuuntelija;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;

/**
 * Graafinen käyttöliittymä yatzy-pelille. Luo ikkunan ja käyttöliittymän
 * komponentit.
 */
public class Kayttoliittyma implements Runnable, Paivitettava {

    private JFrame frame;
    private Peli peli;
    private int pelaajienMaksimiMaara;
    private List<Paivitettava> paivitettavat;

    /**
     * Konstruoi Kayttoliittyma-olion.
     *
     * @param peli peli-olio, jota tämä käyttöliittymä esittää
     */
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
        this.pelaajienMaksimiMaara = 4;
        this.paivitettavat = new ArrayList<>();
    }

    @Override
    public void paivita() {
        for (Paivitettava p : this.paivitettavat) {
            p.paivita();
        }
    }

    @Override
    public void run() {
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(new Dimension(480, 360));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoPelaajienLisaysRuutu(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     * Avaa varsinaisen peli-ikkunan.
     */
    public void aloitaPeli() {
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(new Dimension(1280, 720));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void luoPelaajienLisaysRuutu(Container container) {
        JPanel paneeli = new JPanel();
        paneeli.setPreferredSize(new Dimension(480, 360));
        paneeli.setLayout(new GridBagLayout());
        GridBagConstraints rajat = new GridBagConstraints();
        rajat.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < this.pelaajienMaksimiMaara; i++) {
            rajat.gridy = i;
            JLabel pelaajaNumero = new JLabel("Pelaaja " + (i + 1) + ": ");
            paneeli.add(pelaajaNumero, rajat);

        }
        rajat.gridx = 1;
        for (int i = 0; i < this.pelaajienMaksimiMaara; i++) {
            rajat.gridy = i;
            JTextField nimiKentta = new JTextField(20);
            nimiKentta.addActionListener(new PelaajanLisaysKuuntelija(this.peli, this));
            paneeli.add(nimiKentta, rajat);
        }
        PelinAloitusNappi pelinAloitusNappi = new PelinAloitusNappi("Aloita peli", this.peli);
        pelinAloitusNappi.addActionListener(new PelinAloitusKuuntelija(this));
        this.paivitettavat.add(pelinAloitusNappi);
        paneeli.add(pelinAloitusNappi);
        this.paivita();
        container.add(paneeli);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridBagLayout());
        JPanel pistelista = this.luoPisteLista();
        JPanel nopanHeittoPaneeli = this.luoNopanHeittoPaneeli();
        container.add(pistelista);
        container.add(nopanHeittoPaneeli);
        this.paivita();
    }

    private JPanel luoPisteLista() {
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
        pisteListaRajat.gridy = 0;
        for (int i = 0; i < this.peli.getPelaajat().size(); i++) {
            pisteListaRajat.gridx = i + 1;
            JLabel nimiLabel = new JLabel(this.peli.getPelaajat().get(i).getNimi());
            paneeli.add(nimiLabel, pisteListaRajat);
        }
        //pistetaulukko väliin
        for (int j = 1; j <= this.peli.getPelaajat().size(); j++) {
            Pelaaja pelaaja = this.peli.getPelaajat().get(j - 1);
            pisteListaRajat.gridx = j;
            pisteListaRajat.gridy = 1;
            for (int i = 0; i < KierrosNimet.kierrosNimet().length; i++) {
                PisteListaNappi nappi = new PisteListaNappi("", KierrosNimet.kierrosNimet()[i], pelaaja, this.peli);
                paneeli.add(nappi, pisteListaRajat);
                this.paivitettavat.add(nappi);
                pisteListaRajat.gridy++;
            }
            PisteListaNappi yht = new PisteListaNappi("", "yhteensä", pelaaja, this.peli);
            yht.setEnabled(false);
            this.paivitettavat.add(yht);
            paneeli.add(yht, pisteListaRajat);
        }
        return paneeli;
    }

    private JPanel luoNopanHeittoPaneeli() {
        JPanel paneeli = new JPanel();
        GridBagConstraints rajat = new GridBagConstraints();
        rajat.fill = GridBagConstraints.HORIZONTAL;
        //noppia kuvaavat napit
        for (Noppa noppa : this.peli.getNopat()) {
            NoppaNappi nappi = new NoppaNappi("", noppa, this.peli);
            this.paivitettavat.add(nappi);
            nappi.addActionListener(new NopanValintaKuuntelija(this.peli, this));
            paneeli.add(nappi, rajat);
            rajat.gridx++;
        }
        //valitut nopat heittävä nappi
        NopanHeittoNappi nopanHeittoNappi = new NopanHeittoNappi("Heitä nopat", this.peli);
        nopanHeittoNappi.addActionListener(new NopanHeittoKuuntelija(this.peli, this));
        this.paivitettavat.add(nopanHeittoNappi);
        paneeli.add(nopanHeittoNappi, rajat);
        return paneeli;
    }
}
