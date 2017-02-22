package ohha.yavatzy.kayttoliittyma;

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
import ohha.yavatzy.sovelluslogiikka.Pelaaja;

import ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat.*;
import ohha.yavatzy.kayttoliittyma.napit.*;

import ohha.yavatzy.kayttoliittyma.Paivitettava;
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
    /**
     * Avaa pelaajien lisäys -ruudun.
     */
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
     * Aloittaa uuden pelin samoilla pelaajilla.
     */
    public void uusiPeli() {
        Peli uusiPeli = new Peli(null, this.peli.getPelaajat());
        this.peli = new Peli(null, this.peli.getPelaajat());
        this.paivitettavat.clear();
        this.aloitaPeli();
    }

    /**
     * Avaa varsinaisen peli-ikkunan.
     */
    public void aloitaPeli() {
        this.frame.setVisible(false);
        this.frame.dispose();
        this.frame = new JFrame("Yavatzy");
        this.frame.setPreferredSize(new Dimension(800, 800));
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.luoKomponentit(frame.getContentPane());
        this.frame.pack();
        this.frame.setVisible(true);
        this.paivita();
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
        rajat.gridy = this.pelaajienMaksimiMaara + 1;
        PelinAloitusNappi pelinAloitusNappi = new PelinAloitusNappi("Aloita peli", this.peli);
        pelinAloitusNappi.addActionListener(new PelinAloitusKuuntelija(this));
        this.paivitettavat.add(pelinAloitusNappi);
        paneeli.add(pelinAloitusNappi, rajat);
        this.paivita();
        container.add(paneeli);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints ulkoRajat = new GridBagConstraints();
        JPanel pistelista = this.luoPisteLista();
        JPanel nopanHeittoPaneeli = this.luoNopanHeittoPaneeli();
        container.add(pistelista, ulkoRajat);
        ulkoRajat.gridy = 1;
        container.add(nopanHeittoPaneeli, ulkoRajat);
        this.paivita();
    }

    private JPanel luoPisteLista() {
        JPanel paneeli = new JPanel();
        paneeli.setPreferredSize(new Dimension(100 + this.peli.getPelaajat().size() * 200, 800));
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
                nappi.addActionListener(new PisteListaKuuntelija(this.peli, this));
                paneeli.add(nappi, pisteListaRajat);
                this.paivitettavat.add(nappi);
                pisteListaRajat.gridy++;
            }
            PisteListaNappi yht = new PisteListaNappi("", "yhteensä", pelaaja, this.peli);
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
        // uusi peli -nappi
        rajat.gridx++;
        UusiPeliNappi nappi = new UusiPeliNappi("Uusi peli", this.peli);
        nappi.addActionListener(new UusiPeliKuuntelija(this));
        this.paivitettavat.add(nappi);
        paneeli.add(nappi, rajat);
        return paneeli;
    }
}
