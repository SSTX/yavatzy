package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ohha.yavatzy.TestRandom;
import ohha.yavatzy.sovelluslogiikka.Noppa;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

public class PisteytyssaannotTest {

    Pisteytyssaannot saannot;
    List<Noppa> nopatPieniSuora;
    List<Noppa> nopatKaksiParia;
    List<Noppa> nopatTayskasi;
    List<Noppa> nopatNeljaSamaa;
    List<Noppa> nopatViisiSamaa;
    List<Noppa> nopatIsoSuora;

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
        nopatKaksiParia = luoNopat(new int[]{1, 2, 3, 1, 2});
        nopatPieniSuora = luoNopat(new int[]{1, 2, 3, 4, 5});
        nopatIsoSuora = luoNopat(new int[]{2, 3, 4, 5, 6});
        nopatTayskasi = luoNopat(new int[]{3, 3, 4, 4, 4});
        nopatNeljaSamaa = luoNopat(new int[]{5, 5, 5, 5, 1});
        nopatViisiSamaa = luoNopat(new int[]{6, 6, 6, 6, 6});
    }

    @Test
    public void montaSamaaPalauttaaNollaJosKaikissaNopissaEriPisteluku() {
        assertEquals(0, saannot.montaSamaa(nopatPieniSuora, 2));
        assertEquals(0, saannot.montaSamaa(nopatPieniSuora, 3));
        assertEquals(0, saannot.montaSamaa(nopatPieniSuora, 4));
        assertEquals(0, saannot.yatzy(nopatPieniSuora));
    }

    @Test
    public void montaSamaaPalauttaaNollaJosPistelukujaVahemmanKuinVaadittu() {
        assertEquals(0, saannot.montaSamaa(nopatTayskasi, 4));
    }

    @Test
    public void montaSamaaEiOtaHuomioonEnempaaNoppiaKuinOnMaaritetty() {
        assertEquals(10, saannot.montaSamaa(nopatNeljaSamaa, 2));
        assertEquals(8, saannot.montaSamaa(nopatTayskasi, 2));
        assertEquals(15, saannot.montaSamaa(nopatNeljaSamaa, 3));
        assertEquals(24, saannot.montaSamaa(nopatViisiSamaa, 4));
    }

    @Test
    public void pariPalauttaaSuurimmanSummanJosUseitaVaihtoehtoja() {
        assertEquals(4, saannot.montaSamaa(nopatKaksiParia, 2));
    }

    @Test
    public void kolmoislukuPalauttaaOikeanSummanJosOnKolmeSamaa() {
        assertEquals(12, saannot.montaSamaa(nopatTayskasi, 3));
    }

    @Test
    public void neloislukuPalauttaaOikeanSummanJosOnNeljaSamaa() {
        assertEquals(20, saannot.montaSamaa(nopatNeljaSamaa, 4));
    }

    @Test
    public void yatzyPalauttaaOikeanSummanJosOnViisiSamaa() {
        assertEquals(50, saannot.yatzy(nopatViisiSamaa));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaNollaJosHaluttuaPistelukuaEiOle() {
        assertEquals(0, saannot.laskePistelukujenSumma(nopatTayskasi, 1));
        assertEquals(0, saannot.laskePistelukujenSumma(nopatViisiSamaa, 3));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaOikeanSummanJosPistelukujaLoytyy() {
        assertEquals(5, saannot.laskePistelukujenSumma(nopatPieniSuora, 5));
        assertEquals(4, saannot.laskePistelukujenSumma(nopatKaksiParia, 2));
        assertEquals(30, saannot.laskePistelukujenSumma(nopatViisiSamaa, 6));
    }

    @Test
    public void tayskasiPalauttaaNollaJosEiKaksiSamaaJaKolmeSamaa() {
        assertEquals(0, saannot.tayskasi(nopatPieniSuora));
        assertEquals(0, saannot.tayskasi(nopatViisiSamaa));
        assertEquals(0, saannot.tayskasi(nopatNeljaSamaa));
        assertEquals(0, saannot.tayskasi(nopatKaksiParia));
    }
    
    @Test
    public void taysKasiPalauttaaOikeanSummanJosOnKaksiSamaaJaKolmeSamaa() {
        assertEquals(18, saannot.tayskasi(nopatTayskasi));
    }
    
    @Test
    public void pieniSuoraPalauttaaNollaJosEiPientaSuoraa() {
        assertEquals(0, saannot.pieniSuora(nopatKaksiParia));
        assertEquals(0, saannot.pieniSuora(nopatViisiSamaa));
        assertEquals(0, saannot.pieniSuora(nopatIsoSuora));
    }
    
    @Test
    public void pieniSuoraPalauttaaOikeinJosOnPieniSuora() {
        assertEquals(15, saannot.pieniSuora(nopatPieniSuora));
    }
    
     @Test
    public void isoSuoraPalauttaaNollaJosEiIsoaSuoraa() {
        assertEquals(0, saannot.isoSuora(nopatKaksiParia));
        assertEquals(0, saannot.isoSuora(nopatViisiSamaa));
        assertEquals(0, saannot.isoSuora(nopatPieniSuora));
    }
    
    @Test
    public void isoSuoraPalauttaaOikeinJosOnIsoSuora() {
        assertEquals(20, saannot.isoSuora(nopatIsoSuora));
    }
    
    @Test
    public void sattumaPalauttaaOikeanSumman() {
        assertEquals(20, saannot.sattuma(nopatIsoSuora));
        assertEquals(18, saannot.sattuma(nopatTayskasi));
        assertEquals(9, saannot.sattuma(nopatKaksiParia));
        assertEquals(30, saannot.sattuma(nopatViisiSamaa));
        assertEquals(21, saannot.sattuma(nopatNeljaSamaa));
    }
    
    @Test
    public void kaksiPariaPalauttaaNollaJosEiKahtaParia() {
        assertEquals(0, saannot.kaksiParia(nopatPieniSuora));
        assertEquals(0, saannot.kaksiParia(nopatViisiSamaa));
        assertEquals(0, saannot.kaksiParia(nopatNeljaSamaa));
    }
    
    @Test
    public void kaksiPariaPalauttaaOikeanSummanJosOnKaksiParia() {
        assertEquals(14, saannot.kaksiParia(nopatTayskasi));
        assertEquals(6, saannot.kaksiParia(nopatKaksiParia));
    }
}
