/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import ohha.yavatzy.sovelluslogiikka.domain.Pelaaja;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttiira
 */
public class VuoroKirjanpitoTest {

    VuoroKirjanpito kirjanpito;

    @Before
    public void setUp() {
        kirjanpito = new VuoroKirjanpito(15);
    }

    @Test
    public void Method() {

    }

    @Test
    public void seuraavaVuoroKasvattaaVuoronumeroa() {
        kirjanpito.lisaaPelaaja(new Pelaaja("a"));
        kirjanpito.lisaaPelaaja(new Pelaaja("b"));
        int alussa = kirjanpito.getVuoroNumero();
        kirjanpito.seuraavaVuoro();
        assertEquals(alussa + 1, kirjanpito.getVuoroNumero());
    }

    @Test
    public void seuraavaVuoroEiKasvataVuoronumeroaYliPelaajienLukumaaran() {
        kirjanpito.lisaaPelaaja(new Pelaaja("a"));
        kirjanpito.lisaaPelaaja(new Pelaaja("b"));
        kirjanpito.lisaaPelaaja(new Pelaaja("c"));
        for (int i = 0; i < 15; i++) {
            kirjanpito.seuraavaVuoro();
            assertTrue(kirjanpito.getVuoroNumero() < kirjanpito.getPelaajat().size());
        }
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
