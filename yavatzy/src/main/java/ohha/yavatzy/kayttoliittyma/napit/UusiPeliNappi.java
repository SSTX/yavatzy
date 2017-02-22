package ohha.yavatzy.kayttoliittyma.napit;

import javax.swing.JButton;

import ohha.yavatzy.sovelluslogiikka.Peli;
import ohha.yavatzy.kayttoliittyma.Paivitettava;

/**
 * Lisätty JButton, joka tietää mihin yatzy-peliin se kuuluu. 
 * Osaa päivittää tilaansa pelin perusteella.
 */
public class UusiPeliNappi extends JButton implements Paivitettava {
    private Peli peli;

    /**
     * Luodaan uusi nappi.
     * @param nimi Napin nimi, annetaan yliluokan konstruktorille.
     * @param peli peli, johon tämä nappi liittyy
     */
    public UusiPeliNappi(String nimi, Peli peli) {
        super(nimi);
        this.peli = peli;
    }

    @Override
    public void paivita() {
        this.setEnabled(this.peli.peliLoppu());
    }
}
