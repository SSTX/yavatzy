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

    @Before
    public void setUp() {
        lista = new Pistelista();
    }

    @Test
    public void pelaajanLisaysLisaaPelaajan() {
        lista.lisaaPelaaja(new Pelaaja("Irmeli"));
        assertEquals(1, lista.getPistelista().keySet().size());
    }

    @Test
    public void pelaajanLisaysPalauttaaTrueJosLisaaminenOnnistui() {
        assertTrue(lista.lisaaPelaaja(new Pelaaja("Herra Boole")));
    }
    
    @Test
    public void eiLisataSamaaPelaajaaKahdesti() {
        lista.lisaaPelaaja(new Pelaaja("Irmeli"));
        lista.lisaaPelaaja(new Pelaaja("Irmeli"));
        assertEquals(1, lista.getPistelista().keySet().size());
    }

    @Test
    public void pisteidenLisaaminenKasvattaaPistelistanKokoaYhdella() {
        Pelaaja irmeli = new Pelaaja("Irmeli");
        lista.lisaaPelaaja(irmeli);
        lista.lisaaPisteet(irmeli, "yatzy", 50);
        assertEquals(1, lista.getPistelista().get(irmeli).keySet().size());
    }

    @Test
    public void pelaajanPisteidenHakeminenPalauttaaOikeatPisteet() {
        Pelaaja irmeli = new Pelaaja("Irmeli");
        lista.lisaaPelaaja(irmeli);
        lista.lisaaPisteet(irmeli, "kolmoset", 3);
        lista.lisaaPisteet(irmeli, "pari", 4);
        assertEquals(3, lista.pelaajanPisteetKierrokselta(irmeli, "kolmoset"));
        assertEquals(4, lista.pelaajanPisteetKierrokselta(irmeli, "pari"));
    }

    @Test
    public void eiLisataSamanKierroksenPisteitaUseasti() {
        Pelaaja irmeli = new Pelaaja("Irmeli");
        lista.lisaaPelaaja(irmeli);
        lista.lisaaPisteet(irmeli, "pari", 2);
        lista.lisaaPisteet(irmeli, "pari", 4);
        assertEquals(2, lista.pelaajanPisteetKierrokselta(irmeli, "pari"));
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
