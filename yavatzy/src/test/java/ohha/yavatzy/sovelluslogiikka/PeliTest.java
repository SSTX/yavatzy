/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ttiira
 */
public class PeliTest {

    Peli peli;

    @Before
    public void setUp() {
        peli = new Peli(5, 6);
    }

    @Test
    public void pelaajienMaaraAlussaNolla() {
        assertEquals(0, peli.getPelaajat().size());
    }

    @Test
    public void lisaaPelaajaLaittaaPelaajalleOikeanMaaranNoppia() {
        peli.lisaaPelaaja("Jalmari");
        assertEquals(5, peli.getPelaajat().get(0).getNopat().size());
    }

    @Test
    public void lisaaPelaajaKasvattaaPelaajienMaaraaYhdella() {
        peli.lisaaPelaaja("Pekka");
        assertEquals(1, peli.getPelaajat().size());
    }

    @Test
    public void monenPelaajanLisaaminenKasvattaaPelaajienMaaraaOikein() {
        peli.lisaaPelaaja("Herra X");
        peli.lisaaPelaaja("Spede");
        peli.lisaaPelaaja("Ferdinand");
        assertEquals(3, peli.getPelaajat().size());
    }

    @Test
    public void samaaPelaajaaEiVoiLisataUseasti() {
        peli.lisaaPelaaja("Jalmari");
        peli.lisaaPelaaja("Jokke");
        peli.lisaaPelaaja("Jalmari");
        assertEquals(2, peli.getPelaajat().size());
    }

    @Test
    public void seuraavaVuoroEiKasvataVuoronumeroaYliPelaajienLukumaaran() {
        peli.lisaaPelaaja("a");
        peli.lisaaPelaaja("b");
        peli.lisaaPelaaja("c");
        for (int i = 0; i < 15; i++) {
            peli.seuraavaVuoro();
            assertTrue(peli.getVuoroNumero() < peli.pelaajienMaara());
        }
    }

    @Test
    public void seuraavaVuoroKasvattaaVuoronumeroa() {
        peli.lisaaPelaaja("a");
        peli.lisaaPelaaja("b");
        int alussa = peli.getVuoroNumero();
        peli.seuraavaVuoro();
        assertEquals(alussa + 1, peli.getVuoroNumero());
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
