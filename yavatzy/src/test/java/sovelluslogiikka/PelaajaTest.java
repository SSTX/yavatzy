/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.ArrayList;
import ohha2017_3.yavatzy.sovelluslogiikka.Noppa;
import ohha2017_3.yavatzy.sovelluslogiikka.Pelaaja;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ttiira
 */
public class PelaajaTest {
    
    Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        ArrayList<Noppa> nopat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nopat.add(new Noppa(6, new TestRandom(i)));
        }
        pelaaja = new Pelaaja("Seppo", nopat);
    }
    
    @Test
    public void nykyisetPisteluvutPalautaaOikeatPisteluvut() {
        for (int i = 0; i < pelaaja.getNopat().size(); i++) {
            assertEquals((long)i + 2, (long)pelaaja.nykyisetPisteluvut().get(i));
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
