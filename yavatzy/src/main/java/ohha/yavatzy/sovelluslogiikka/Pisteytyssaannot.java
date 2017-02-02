/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author ttiira
 */
public class Pisteytyssaannot {
    private int yatzyPisteet;

    public Pisteytyssaannot() {
        this.yatzyPisteet = 50;
    }

    public int samat(List<Noppa> nopat, int kuinkaMonta) {
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
        for (int i : nopat.stream().map(Noppa::getPisteluku)
                .collect(Collectors.toList())) {
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
        return this.samat(nopat, 2);
    }

    public int kolmoisluku(List<Noppa> nopat) {
        return this.samat(nopat, 3);
    }

    public int neloisluku(List<Noppa> nopat) {
        return this.samat(nopat, 4);
    }

    public int yatzy(List<Noppa> nopat) {
        if (this.samat(nopat, 5) > 0) {
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


}
