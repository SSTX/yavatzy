/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
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
    public void lisaaPelaajaLaittaaPelaajalleOikeanMaaranNoppia() {
        peli.lisaaPelaaja("Jalmari");
        assertEquals(5, peli.getNopat().size());
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
            assertTrue(peli.getVuoroNumero() < peli.getPelaajat().size());
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

    @Test
    public void nykyisetPisteluvutPalauttaaOikeatPisteluvut() {
        for (int i = 0; i < peli.getNopat().size(); i++) {
            assertEquals((long) i + 2, (long) peli.nykyisetPisteluvut().get(i));
        }
    }

    @Test
    public void heitaNopatMuuttaaOikeidenNoppienPistelukuja() {
        int[] luvut = {0, 1, 3, 4};
        for (int i : luvut) {
            peli.valitseNoppa(peli.getNopat().get(i));
        }
        peli.heitaNopat();
        //indeksi 2 ei muutu
        assertEquals(4, peli.getNopat().get(2).getPisteluku());
        //indeksit 0, 1, 3, 4 muuttuvat
        assertEquals(3, peli.getNopat().get(0).getPisteluku());
        assertEquals(4, peli.getNopat().get(1).getPisteluku());
        assertEquals(6, peli.getNopat().get(3).getPisteluku());
        assertEquals(1, peli.getNopat().get(4).getPisteluku());
    }

    @Test
    public void onNopatVaikkaEiAnnettuParametrina() {
        Peli testi = new Peli();
        assertFalse(testi.getNopat() == null);
        assertTrue(testi.getNopat().size() > 0);
    }

    @Test
    public void noppienPisteluvutEivatMuutuJosEiVoidaHeittaa() {
        int count = 0;
        while (count < 100 && peli.voidaanHeittaa()) {
            peli.heitaNopat();
        }
        List<Integer> pisteluvut = peli.nykyisetPisteluvut();
        peli.heitaNopat();
        for (int i = 0; i < pisteluvut.size(); i++) {
            assertEquals(pisteluvut.get(i), peli.nykyisetPisteluvut().get(i));
        }
    }

    @Test
    public void heitonJalkeenEiHeitettavaksiValittujaNoppia() {
        peli.valitseNoppa(peli.getNopat().get(0));
        peli.heitaNopat();
        assertTrue(peli.getValitutNopat().isEmpty());
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
        peli.getPelaajat().stream().forEach((pelaaja) -> assertEquals(20,
               (long) peli.getPistelista().pelaajanPisteetKierrokselta(pelaaja, "sattuma")));
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
