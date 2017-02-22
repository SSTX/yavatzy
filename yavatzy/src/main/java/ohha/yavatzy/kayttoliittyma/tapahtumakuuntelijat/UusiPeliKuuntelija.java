package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ohha.yavatzy.kayttoliittyma.Kayttoliittyma;

/**
 * ActionListener-rajapinnan toteuttava luokka, jonka tehtävänä on laukaista yatzyn uusi peli -toiminto.
 */
public class UusiPeliKuuntelija implements ActionListener {

    private Kayttoliittyma kayttoliittyma;

    /**
     * Luodaan tapahtumakuuntelija.
     * @param kayttoliittyma Käyttöliittymä, jonka tapahtumia tämä kuuntelija kuuntelee.
     */
    public UusiPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.kayttoliittyma.uusiPeli();
    }
}
