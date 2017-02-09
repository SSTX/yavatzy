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
        assertEquals(0, saannot.pisteyta("pari", nopatPieniSuora));
        assertEquals(0, saannot.pisteyta("kolmoisluku", nopatPieniSuora));
        assertEquals(0, saannot.pisteyta("neloisluku", nopatPieniSuora));
        assertEquals(0, saannot.pisteyta("yatzy", nopatPieniSuora));
    }

    @Test
    public void montaSamaaPalauttaaNollaJosPistelukujaVahemmanKuinVaadittu() {
        assertEquals(0, saannot.pisteyta("neloisluku", nopatTayskasi));
    }

    @Test
    public void montaSamaaEiOtaHuomioonEnempaaNoppiaKuinOnMaaritetty() {
        assertEquals(10, saannot.pisteyta("pari", nopatNeljaSamaa));
        assertEquals(8, saannot.pisteyta("pari", nopatTayskasi));
        assertEquals(15, saannot.pisteyta("kolmoisluku", nopatNeljaSamaa));
        assertEquals(24, saannot.pisteyta("neloisluku", nopatViisiSamaa));
    }

    @Test
    public void pariPalauttaaSuurimmanSummanJosUseitaVaihtoehtoja() {
        assertEquals(4, saannot.pisteyta("pari", nopatKaksiParia));
    }

    @Test
    public void kolmoislukuPalauttaaOikeanSummanJosOnKolmeSamaa() {
        assertEquals(12, saannot.pisteyta("kolmoisluku", nopatTayskasi));
    }

    @Test
    public void neloislukuPalauttaaOikeanSummanJosOnNeljaSamaa() {
        assertEquals(20, saannot.pisteyta("neloisluku", nopatNeljaSamaa));
    }

    @Test
    public void yatzyPalauttaaOikeanSummanJosOnViisiSamaa() {
        assertEquals(50, saannot.pisteyta("yatzy", nopatViisiSamaa));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaNollaJosHaluttuaPistelukuaEiOle() {
        assertEquals(0, saannot.pisteyta("ykköset", nopatTayskasi));
        assertEquals(0, saannot.pisteyta("kolmoset", nopatViisiSamaa));
    }

    @Test
    public void laskePistelukujenSummaPalauttaaOikeanSummanJosPistelukujaLoytyy() {
        assertEquals(5, saannot.pisteyta("vitoset", nopatPieniSuora));
        assertEquals(4, saannot.pisteyta("kakkoset", nopatKaksiParia));
        assertEquals(30, saannot.pisteyta("kutoset", nopatViisiSamaa));
    }

    @Test
    public void tayskasiPalauttaaNollaJosEiKaksiSamaaJaKolmeSamaa() {
        assertEquals(0, saannot.pisteyta("täyskäsi", nopatPieniSuora));
        assertEquals(0, saannot.pisteyta("täyskäsi", nopatViisiSamaa));
        assertEquals(0, saannot.pisteyta("täyskäsi",  nopatNeljaSamaa));
        assertEquals(0, saannot.pisteyta("täyskäsi", nopatKaksiParia));
    }
    
    @Test
    public void taysKasiPalauttaaOikeanSummanJosOnKaksiSamaaJaKolmeSamaa() {
        assertEquals(18, saannot.pisteyta("täyskäsi", nopatTayskasi));
    }
    
    @Test
    public void pieniSuoraPalauttaaNollaJosEiPientaSuoraa() {
        assertEquals(0, saannot.pisteyta("pieni suora", nopatKaksiParia));
        assertEquals(0, saannot.pisteyta("pieni suora", nopatViisiSamaa));
        assertEquals(0, saannot.pisteyta("pieni suora", nopatIsoSuora));
    }
    
    @Test
    public void pieniSuoraPalauttaaOikeinJosOnPieniSuora() {
        assertEquals(15, saannot.pisteyta("pieni suora", nopatPieniSuora));
    }
    
     @Test
    public void isoSuoraPalauttaaNollaJosEiIsoaSuoraa() {
        assertEquals(0, saannot.pisteyta("iso suora", nopatKaksiParia));
        assertEquals(0, saannot.pisteyta("iso suora", nopatViisiSamaa));
        assertEquals(0, saannot.pisteyta("iso suora", nopatPieniSuora));
    }
    
    @Test
    public void isoSuoraPalauttaaOikeinJosOnIsoSuora() {
        assertEquals(20, saannot.pisteyta("iso suora", nopatIsoSuora));
    }
    
    @Test
    public void sattumaPalauttaaOikeanSumman() {
        assertEquals(20, saannot.pisteyta("sattuma", nopatIsoSuora));
        assertEquals(18, saannot.pisteyta("sattuma", nopatTayskasi));
        assertEquals(9, saannot.pisteyta("sattuma", nopatKaksiParia));
        assertEquals(30, saannot.pisteyta("sattuma", nopatViisiSamaa));
        assertEquals(21, saannot.pisteyta("sattuma", nopatNeljaSamaa));
    }
    
    @Test
    public void kaksiPariaPalauttaaNollaJosEiKahtaParia() {
        assertEquals(0, saannot.pisteyta("kaksi paria", nopatPieniSuora));
        assertEquals(0, saannot.pisteyta("kaksi paria", nopatViisiSamaa));
        assertEquals(0, saannot.pisteyta("kaksi paria", nopatNeljaSamaa));
    }
    
    @Test
    public void kaksiPariaPalauttaaOikeanSummanJosOnKaksiParia() {
        assertEquals(14, saannot.pisteyta("kaksi paria", nopatTayskasi));
        assertEquals(6, saannot.pisteyta("kaksi paria", nopatKaksiParia));
    }
}
