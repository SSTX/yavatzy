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
        assertEquals(0, saannot.kolmoisluku(nopatEiSamoja));
        assertEquals(0, saannot.neloisluku(nopatEiSamoja));
        assertEquals(0, saannot.yatzy(nopatEiSamoja));
    }

    @Test
    public void samatPalauttaaNollaJosPistelukujaVahemmanKuinVaadittu() {
        assertEquals(0, saannot.neloisluku(nopatTayskasi));
    }

    @Test
    public void samatEiOtaHuomioonEnempaaNoppiaKuinOnMaaritetty() {
        assertEquals(10, saannot.pari(nopatNeljaSamaa));
        assertEquals(8, saannot.pari(nopatTayskasi));
        assertEquals(15, saannot.kolmoisluku(nopatNeljaSamaa));
        assertEquals(24, saannot.neloisluku(nopatViisiSamaa));
    }
    
    @Test
    public void pariPalauttaaSuurimmanSummanJosUseitaVaihtoehtoja() {
        assertEquals(4, saannot.pari(nopatKaksiParia));
    }

    @Test
    public void kolmoislukuPalauttaaOikeanSummanJosOnKolmeSamaa() {
        assertEquals(12, saannot.kolmoisluku(nopatTayskasi));
    }

    @Test
    public void neloislukuPalauttaaOikeanSummanJosOnNeljaSamaa() {
        assertEquals(20, saannot.neloisluku(nopatNeljaSamaa));
    }

    @Test
    public void yatzyPalauttaaOikeanSummanJosOnViisiSamaa() {
        assertEquals(50, saannot.yatzy(nopatViisiSamaa));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaNollaJosHaluttuaPistelukuaEiOle() {
        assertEquals(0, saannot.ykkoset(nopatTayskasi));
        assertEquals(0, saannot.kolmoset(nopatViisiSamaa));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaOikeanSummanJosPistelukujaLoytyy() {
        assertEquals(5, saannot.vitoset(nopatEiSamoja));
        assertEquals(4, saannot.kakkoset(nopatKaksiParia));
        assertEquals(30, saannot.kutoset(nopatViisiSamaa));
    }
}

