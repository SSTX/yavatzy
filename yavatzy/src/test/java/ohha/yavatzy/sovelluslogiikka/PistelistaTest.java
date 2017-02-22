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
public class PistelistaTest {

    Pistelista lista;
    Pelaaja jalmari;

    @Before
    public void setUp() {
        lista = new Pistelista();
        jalmari = new Pelaaja("Jalmari");
        lista.lisaaPelaaja(jalmari);
    }

    @Test
    public void pelaajanLisaysLisaaPelaajan() {
        assertEquals(1, lista.getPistelista().keySet().size());
        lista.lisaaPelaaja(new Pelaaja("Irmeli"));
        assertEquals(2, lista.getPistelista().keySet().size());
    }

    @Test
    public void pelaajanLisaysPalauttaaTrueJosLisaaminenOnnistui() {
        assertTrue(lista.lisaaPelaaja(new Pelaaja("Herra Boole")));
    }

    @Test
    public void eiLisataSamaaPelaajaaKahdesti() {
        lista.lisaaPelaaja(new Pelaaja("Jalmari"));
        assertEquals(1, lista.getPistelista().keySet().size());
    }

    @Test
    public void pelaajanPisteidenHakeminenPalauttaaOikeatPisteet() {
        Pelaaja irmeli = new Pelaaja("Irmeli");
        lista.lisaaPelaaja(irmeli);
        lista.lisaaPisteet(irmeli, "kolmoset", 3);
        lista.lisaaPisteet(irmeli, "pari", 4);
        assertEquals(3, (long)lista.pelaajanPisteetKierrokselta(irmeli, "kolmoset"));
        assertEquals(4, (long)lista.pelaajanPisteetKierrokselta(irmeli, "pari"));
    }

    @Test
    public void eiLisataSamanKierroksenPisteitaUseasti() {
        Pelaaja irmeli = new Pelaaja("Irmeli");
        lista.lisaaPelaaja(irmeli);
        lista.lisaaPisteet(irmeli, "pari", 2);
        lista.lisaaPisteet(irmeli, "pari", 4);
        assertEquals(2, (long)lista.pelaajanPisteetKierrokselta(irmeli, "pari"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikein1() {
        assertEquals(0, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikein2() {
        lista.lisaaPisteet(jalmari, "pari", 2);
        lista.lisaaPisteet(jalmari, "kolmoisluku", 9);
        assertEquals(11, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikein3() {
        lista.lisaaPisteet(jalmari, "yatzy", 50);
        lista.lisaaPisteet(jalmari, "neloisluku", 24);
        lista.lisaaPisteet(jalmari, "kutoset", 30);
        assertEquals(104, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikeinBonus1() {
        lista.lisaaPisteet(jalmari, "kutoset", 30);
        lista.lisaaPisteet(jalmari, "vitoset", 25);
        lista.lisaaPisteet(jalmari, "neloset", 8);
        assertEquals(113, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikeinBonus2() {
        lista.lisaaPisteet(jalmari, "kutoset", 30);
        lista.lisaaPisteet(jalmari, "vitoset", 25);
        lista.lisaaPisteet(jalmari, "kolmoset", 9);
        assertEquals(114, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
    }

    @Test
    public void pelaajanKokonaisPisteetOikeinBonus3() {
        lista.lisaaPisteet(jalmari, "kutoset", 30);
        lista.lisaaPisteet(jalmari, "neloset", 20);
        lista.lisaaPisteet(jalmari, "kolmoset", 12);
        assertEquals(62, (long)lista.pelaajanPisteetKierrokselta(jalmari, "yhteensä"));
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
