/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ohha.yavatzy.TestRandom;
import ohha.yavatzy.sovelluslogiikka.Noppa;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author ttiira
 */
public class PelaajaTest {

    Pelaaja pelaaja;
    Pelaaja pelaaja2;

    @Before
    public void setUp() {
        ArrayList<Noppa> nopat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nopat.add(new Noppa(6, new TestRandom(i)));
        }
        pelaaja = new Pelaaja("Seppo", nopat);
        pelaaja2 = new Pelaaja("", nopat);
    }

    @Test
    public void pelaajanNimiEiTyhja() {
        assertFalse(pelaaja2.getNimi().isEmpty());
    }
    
    @Test
    public void pelaajanToStringOikein() {
        assertEquals("Seppo: 2 3 4 5 6", pelaaja.toString());
    }
    

    @Test
    public void nykyisetPisteluvutPalauttaaOikeatPisteluvut() {
        for (int i = 0; i < pelaaja.getNopat().size(); i++) {
            assertEquals((long) i + 2, (long) pelaaja.nykyisetPisteluvut().get(i));
        }
    }

    @Test
    public void heitaNopatHylkaaOlemattomatNopat() {
        int[] luvut = {-1, 6, 10};
        List<Integer> lista = new ArrayList<>();
        for (int i : luvut) {
            lista.add(i);
        }
        pelaaja.heitaNopat(lista);
        for (int i = 0; i < 5; i++) {
            assertEquals((long) i + 2, pelaaja.getNopat().get(i).getPisteluku());
        }
    }

    @Test
    public void heitaNopatMuuttaaOikeidenNoppienPistelukuja() {
        int[] luvut = {0, 1, 3, 4};
        List<Integer> lista = new ArrayList<>();
        for (int i : luvut) {
            lista.add(i);
        }
        pelaaja.heitaNopat(lista);
        //indeksi 2 ei muutu
        assertEquals(4, pelaaja.getNopat().get(2).getPisteluku());
        //indeksit 0, 1, 3, 4 muuttuvat
        assertEquals(3, pelaaja.getNopat().get(0).getPisteluku());
        assertEquals(4, pelaaja.getNopat().get(1).getPisteluku());
        assertEquals(6, pelaaja.getNopat().get(3).getPisteluku());
        assertEquals(1, pelaaja.getNopat().get(4).getPisteluku());
    }

    @After
    public void tearDown() {
    }
}
