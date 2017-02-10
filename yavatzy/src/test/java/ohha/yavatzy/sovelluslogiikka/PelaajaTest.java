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
    



    @After
    public void tearDown() {
    }
}
