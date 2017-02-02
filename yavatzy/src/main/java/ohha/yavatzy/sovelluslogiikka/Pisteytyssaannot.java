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

    public int samat(List<Noppa> nopat, int kuinkaMonta) {
        Map<Integer, List<Noppa>> pisteluvuittain = nopat.stream()
            .collect(Collectors.groupingBy(Noppa::getPisteluku));
        int suurin = 0;
        for (int i : pisteluvuittain.keySet()) {
            int lkm = pisteluvuittain.get(i).size();
            int tulos = Math.min(lkm, kuinkaMonta) * i;
            if (lkm >= kuinkaMonta && tulos > suurin) {
                suurin = tulos;
            }
        }
        return suurin;
    }

    public int pari(List<Noppa> nopat) {
        return this.samat(nopat, 2);
    }

    public int kolmoset(List<Noppa> nopat) {
        return this.samat(nopat, 3);
    }

    public int neloset(List<Noppa> nopat) {
        return this.samat(nopat, 4);
    }

    public int vitoset(List<Noppa> nopat) {
        return this.samat(nopat, 5);
    }
}
