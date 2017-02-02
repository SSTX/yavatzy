package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ohha.yavatzy.TestRandom;
import ohha.yavatzy.sovelluslogiikka.Noppa;
import static org.junit.Assert.assertEquals;

public class PisteytyssaannotTest {

    Pisteytyssaannot saannot;
    List<Noppa> nopatEiSamoja;
    List<Noppa> nopatKaksiParia;
    List<Noppa> nopatTayskasi;
    List<Noppa> nopatNeljaSamaa;
    List<Noppa> nopatViisiSamaa;

    private List<Noppa> luoNopat(int[] pisteluvut) {
        List<Noppa> nopat = new ArrayList<>();
        for (int i : pisteluvut) {
            nopat.add(new Noppa(new TestRandom(i - 2)));
        }
        return nopat;
    }
    
    @Before
    public void setUp() {
        saannot = new Pisteytyssaannot();
        nopatKaksiParia = luoNopat(new int[]{1,2,3,1,2});
        nopatEiSamoja = luoNopat(new int[]{1,2,3,4,5});
        nopatTayskasi = luoNopat(new int[]{3,3,4,4,4});
        nopatNeljaSamaa = luoNopat(new int[]{5,5,5,5,1});
        nopatViisiSamaa = luoNopat(new int[]{6,6,6,6,6});
    }

    @Test
    public void samatPalauttaaNollaJosKaikissaNopissaEriPisteluku() {
        assertEquals(0, saannot.pari(nopatEiSamoja));
        assertEquals(0, saannot.kolmoset(nopatEiSamoja));
        assertEquals(0, saannot.neloset(nopatEiSamoja));
        assertEquals(0, saannot.vitoset(nopatEiSamoja));
    }

    @Test
    public void samatEiOtaHuomioonEnempaaNoppiaKuinOnMaaritetty() {
        assertEquals(10, saannot.pari(nopatNeljaSamaa));
        assertEquals(8, saannot.pari(nopatTayskasi));
        assertEquals(15, saannot.kolmoset(nopatNeljaSamaa));
        assertEquals(24, saannot.neloset(nopatViisiSamaa));
    }
    
    @Test
    public void pariPalauttaaSuurimmanSummanJosUseitaVaihtoehtoja() {
        assertEquals(4, saannot.pari(nopatKaksiParia));
    }

    @Test
    public void kolmosetPalauttaaOikeanSummanJosOnKolmeSamaa() {
        assertEquals(12, saannot.kolmoset(nopatTayskasi));
    }

    @Test
    public void nelosetPalauttaaOikeanSummanJosOnNeljaSamaa() {
        assertEquals(20, saannot.neloset(nopatNeljaSamaa));
    }

    @Test
    public void vitosetPalauttaaOikeanSummanJosOnViisiSamaa() {
        assertEquals(30, saannot.vitoset(nopatViisiSamaa));
    }
}
