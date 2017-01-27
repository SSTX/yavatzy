/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import ohha.yavatzy.sovelluslogiikka.Peli;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
