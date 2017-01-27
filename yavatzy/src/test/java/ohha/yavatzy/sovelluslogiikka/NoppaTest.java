/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import ohha.yavatzy.sovelluslogiikka.Noppa;
import ohha.yavatzy.TestRandom;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    

}
