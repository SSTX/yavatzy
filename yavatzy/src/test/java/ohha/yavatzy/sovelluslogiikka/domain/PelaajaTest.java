/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka.domain;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author ttiira
 */
public class PelaajaTest {

    Pelaaja pelaaja;
    Pelaaja pelaaja2;

    @Before
    public void setUp() {

        pelaaja = new Pelaaja("Seppo");
        pelaaja2 = new Pelaaja("");
    }

    @Test
    public void pelaajanNimiEiTyhja() {
        assertFalse(pelaaja2.getNimi().isEmpty());
    }
    
    @Test
    public void pelaajanToStringOikein() {
        assertEquals("Seppo", pelaaja.toString());
    }
    
    @Test
    public void equalsPalauttaaTrueSamallaViitteella() {
        assertTrue(pelaaja.equals(pelaaja));
    }
    
    @Test
    public void equalsPalauttaaTrueJosNimetSamat() {
        Pelaaja seppo = new Pelaaja("Seppo");
        assertTrue(pelaaja.equals(seppo));
    }
    
    @Test
    public void equalsPalauttaaFalseJosNimetEri() {
        assertFalse(pelaaja.equals(pelaaja2));
    }
    



    @After
    public void tearDown() {
    }
}
