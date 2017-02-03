/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author ttiira
 */
public class Pisteytyssaannot {

    private int pieniSuoraPisteet;
    private int isoSuoraPisteet;
    private int yatzyPisteet;
    // liitetään kierroksen nimen ilmaisevaan merkkijonoon pisteytysmetodi
    private Map<String, Function<List<Noppa>, Integer>> metodit;

    public Pisteytyssaannot() {
        this.pieniSuoraPisteet = 15;
        this.isoSuoraPisteet = 20;
        this.yatzyPisteet = 50;
        this.metodit = new HashMap<>();
        // lisätään metodit tauluun
        metodit.put("ykköset", (a) -> {
            return this.ykkoset(a);
        });
        metodit.put("kakkoset", (a) -> {
            return this.kakkoset(a);
        });
        metodit.put("kolmoset", (a) -> {
            return this.kolmoset(a);
        });
        metodit.put("neloset", (a) -> {
            return this.neloset(a);
        });
        metodit.put("vitoset", (a) -> {
            return this.vitoset(a);
        });
        metodit.put("kutoset", (a) -> {
            return this.kutoset(a);
        });
        metodit.put("pari", (a) -> {
            return this.pari(a);
        });
        metodit.put("kaksi paria", (a) -> {
            return this.kaksiParia(a);
        });
        metodit.put("kolmoisluku", (a) -> {
            return this.kolmoisluku(a);
        });
        metodit.put("neloisluku", (a) -> {
            return this.neloisluku(a);
        });
        metodit.put("pieni suora", (a) -> {
            return this.pieniSuora(a);
        });
        metodit.put("iso suora", (a) -> {
            return this.isoSuora(a);
        });
        metodit.put("täyskäsi", (a) -> {
            return this.tayskasi(a);
        });
        metodit.put("sattuma", (a) -> {
            return this.sattuma(a);
        });
        metodit.put("yatzy", (a) -> {
            return this.yatzy(a);
        });
    }

    public int pisteyta(String kierrosNimi, List<Noppa> nopat) {
        if (this.metodit.get(kierrosNimi) != null) {
            return this.metodit.get(kierrosNimi).apply(nopat);
        }
        // halutun nimistä kierrosta ei ole
        return 0;
    }

    public List<Integer> muunnaPisteluvuiksi(List<Noppa> nopat) {
        return nopat.stream().map(Noppa::getPisteluku).collect(Collectors.toList());
    }

    public boolean kaikkiEri(List<Noppa> nopat) {
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        for (int i : pisteluvuittain.keySet()) {
            if (pisteluvuittain.get(i).size() > 1) {
                return false;
            }
        }
        return true;
    }

    public int montaSamaa(List<Noppa> nopat, int kuinkaMonta) {
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        int suurin = 0;
        for (int i : pisteluvuittain.keySet()) {
            int lkm = pisteluvuittain.get(i).size();
            if (lkm >= kuinkaMonta && kuinkaMonta * i > suurin) {
                suurin = kuinkaMonta * i;
            }
        }
        return suurin;
    }

    public int laskePistelukujenSumma(List<Noppa> nopat, int haluttuPisteluku) {
        int summa = 0;
        for (int i : this.muunnaPisteluvuiksi(nopat)) {
            if (i == haluttuPisteluku) {
                summa += i;
            }
        }
        return summa;
    }

    private Map<Integer, List<Noppa>> ryhmittelePisteluvunMukaan(List<Noppa> nopat) {
        return nopat.stream().collect(Collectors.groupingBy(Noppa::getPisteluku));
    }

    public int pari(List<Noppa> nopat) {
        return this.montaSamaa(nopat, 2);
    }

    public int kaksiParia(List<Noppa> nopat) {
        int pareja = 0;
        int tulos = 0;
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        for (int pisteluku : pisteluvuittain.keySet()) {
            List<Noppa> valitut = pisteluvuittain.get(pisteluku);
            if (valitut.size() >= 2) {
                pareja++;
                tulos += 2 * pisteluku;
            }
        }
        if (pareja >= 2) {
            return tulos;
        }
        return 0;
    }

    public int kolmoisluku(List<Noppa> nopat) {
        return this.montaSamaa(nopat, 3);
    }

    public int neloisluku(List<Noppa> nopat) {
        return this.montaSamaa(nopat, 4);
    }

    public int yatzy(List<Noppa> nopat) {
        if (this.montaSamaa(nopat, 5) > 0) {
            return this.yatzyPisteet;
        }
        return 0;
    }

    public int ykkoset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 1);
    }

    public int kakkoset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 2);
    }

    public int kolmoset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 3);
    }

    public int neloset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 4);
    }

    public int vitoset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 5);
    }

    public int kutoset(List<Noppa> nopat) {
        return this.laskePistelukujenSumma(nopat, 6);
    }

    public int tayskasi(List<Noppa> nopat) {
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        int tulos = 0;
        for (int pisteluku : pisteluvuittain.keySet()) {
            List<Noppa> valitut = pisteluvuittain.get(pisteluku);
            switch (valitut.size()) {
                case 2:
                    tulos += this.pari(valitut);
                    break;
                case 3:
                    tulos += this.kolmoisluku(valitut);
                    break;
                default:
                    //jos on samaa pistelukua muu määrä kuin 2 tai 3, ei ole täyskäsi
                    return 0;
            }
        }
        return tulos;
    }

    public int pieniSuora(List<Noppa> nopat) {
        if (this.kaikkiEri(nopat) && this.muunnaPisteluvuiksi(nopat).contains(1)) {
            return this.pieniSuoraPisteet;
        }
        return 0;
    }

    public int isoSuora(List<Noppa> nopat) {
        if (this.kaikkiEri(nopat) && this.muunnaPisteluvuiksi(nopat).contains(6)) {
            return this.isoSuoraPisteet;
        }
        return 0;
    }

    public int sattuma(List<Noppa> nopat) {
        return nopat.stream().mapToInt(Noppa::getPisteluku).sum();
    }
}
