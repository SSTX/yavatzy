/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import ohha.yavatzy.sovelluslogiikka.domain.Pelaaja;
import ohha.yavatzy.sovelluslogiikka.domain.Noppa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ohha.yavatzy.TestRandom;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        ArrayList<Noppa> nopat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nopat.add(new Noppa(6, new TestRandom(i)));
        }
        peli = new Peli(nopat);
    }

    @Test
    public void pelaajienMaaraAlussaNolla() {
        assertEquals(0, peli.getPelaajat().size());
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
    public void onNopatVaikkaEiAnnettuParametrina() {
        Peli testi = new Peli();
        assertFalse(testi.getNopat() == null);
        assertFalse(testi.getNopat().isEmpty());
    }

 



    @Test
    public void pisteidenLisaysVaihtaaVuorossaOlevaaPelaajaa() {
        peli.lisaaPelaaja("Jalmari");
        peli.lisaaPelaaja("Jokke");
        Pelaaja p = peli.vuorossaOlevaPelaaja();
        peli.lisaaPisteet("sattuma");
        assertFalse(p.equals(peli.vuorossaOlevaPelaaja()));
    }

    @Test
    public void pisteetLisataanJarjestyksessaJokaisellePelaajalle() {
        peli.lisaaPelaaja("Jalmari");
        peli.lisaaPelaaja("Jokke");
        peli.lisaaPisteet("sattuma");
        peli.lisaaPisteet("sattuma");
        assertEquals(20, (long)peli.getPistelista().pelaajanPisteetKierrokselta(peli.getPelaajat().get(0), "sattuma"));
        assertEquals(19, (long)peli.getPistelista().pelaajanPisteetKierrokselta(peli.getPelaajat().get(1), "sattuma"));
    }

    @Test
    public void vuorojenKayttaminenLopettaaPelin() {
        peli.lisaaPelaaja("Jalmari");
        peli.lisaaPelaaja("Jokke");
        for (int i = 0; i < 30; i++) {
            peli.seuraavaVuoro();
        }
        assertTrue(peli.peliLoppu());
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
