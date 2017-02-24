/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ohha.yavatzy.TestRandom;
import ohha.yavatzy.sovelluslogiikka.domain.Noppa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ttiira
 */
public class NopanHeittajaTest {

    NopanHeittaja heittaja;
    List<Noppa> nopat;
    
    private List<Noppa> luoNopat(int... pisteluvut) {
        List<Noppa> nopat = new ArrayList<>();
        for (int i : pisteluvut) {
            nopat.add(new Noppa(new TestRandom(i - 2)));
        }
        return nopat;
    }

    @Before
    public void setUp() {
        nopat = luoNopat(1, 2, 3, 4, 5);
        heittaja = new NopanHeittaja(nopat);
    }

    @Test
    public void heitonJalkeenEiHeitettavaksiValittujaNoppia() {
        heittaja.valitseNoppa(heittaja.getNopat().get(0));
        heittaja.heitaValitutNopat();
        assertTrue(heittaja.getValitutNopat().isEmpty());
    }

    @Test
    public void noppienPisteluvutEivatMuutuJosEiVoidaHeittaa() {
        int count = 0;
        while (count < 100 && heittaja.heittojaJaljella()) {
            heittaja.heitaValitutNopat();
        }
        List<Integer> pisteluvut = heittaja.getNopat().stream().map(Noppa::getPisteluku).collect(Collectors.toList());
        heittaja.heitaValitutNopat();
        for (int i = 0; i < pisteluvut.size(); i++) {
            assertEquals(pisteluvut.get(i), heittaja.getNopat().stream().map(Noppa::getPisteluku).collect(Collectors.toList()).get(i));
        }
    }

    @Test
    public void heitaNopatMuuttaaOikeidenNoppienPistelukuja() {
        int[] luvut = {0, 1, 3, 4};
        for (int i : luvut) {
            heittaja.valitseNoppa(nopat.get(i));
        }
        heittaja.heitaValitutNopat();
        //indeksi 2 ei muutu
        assertEquals(3, heittaja.getNopat().get(2).getPisteluku());
        //indeksit 0, 1, 3, 4 muuttuvat
        assertEquals(2, heittaja.getNopat().get(0).getPisteluku());
        assertEquals(3, heittaja.getNopat().get(1).getPisteluku());
        assertEquals(5, heittaja.getNopat().get(3).getPisteluku());
        assertEquals(6, heittaja.getNopat().get(4).getPisteluku());
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
