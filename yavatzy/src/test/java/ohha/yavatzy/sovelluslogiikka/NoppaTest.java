/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.Random;
import ohha.yavatzy.TestRandom;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author ttiira
 */

public class NoppaTest {

    Noppa noppa;

    @Before
    public void setUp() {
        noppa = new Noppa(6, new TestRandom());
    }
    
    @Test
    public void nopanSivujenMaaraEiAlleYksi() {
        noppa = new Noppa(-1);
        Noppa noppa2 = new Noppa(0);
        assertEquals(1, noppa.getSivujenMaara());
        assertEquals(1, noppa2.getSivujenMaara());
    }

    @Test
    public void nopanPistelukuOikeinAlussa() {
        assertEquals(2, noppa.getPisteluku());
    }
    
    @Test
    public void nopanPistelukuOikeinYhdenHeitonJalkeen() {
        noppa.heita();
        assertEquals(3, noppa.getPisteluku());
    }
    
    @Test
    public void nopanPistelukuOikeinMonenHeitonJalkeen() {
        for (int i = 0; i < 7; i++) {
            noppa.heita();
        }
        assertEquals(3, noppa.getPisteluku());
    }
    
    @Test
    public void heitaPalauttaaOikeanArvon() {
        assertEquals(3, noppa.heita());
    }
    
    @Test
    public void equalsToimiiSamallaViitteella() {
        Noppa testi = noppa;
        assertTrue(testi.equals(noppa));
    }
    
    @Test
    public void equalsToimiiJosRandomOnSama() {
        Random rand = new Random();
        Noppa n1 = new Noppa(rand);
        Noppa n2 = new Noppa(rand);
        assertTrue(n1.equals(n2));
    }
    
    @Test
    public void toStringPalauttaaOikeanArvon() {
        assertEquals("2", noppa.toString());
    }

}
