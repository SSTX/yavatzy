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
    public void samatPalauttaaNollaJosKaikissaNopissaEriPisteluku() {
        assertEquals(0, saannot.pari(nopatPieniSuora));
        assertEquals(0, saannot.kolmoisluku(nopatPieniSuora));
        assertEquals(0, saannot.neloisluku(nopatPieniSuora));
        assertEquals(0, saannot.yatzy(nopatPieniSuora));
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
        assertEquals(5, saannot.vitoset(nopatPieniSuora));
        assertEquals(4, saannot.kakkoset(nopatKaksiParia));
        assertEquals(30, saannot.kutoset(nopatViisiSamaa));
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
