/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ttiira
 */
public class Pisteytyssaannot {

    public int samat(List<Noppa> nopat, int kuinkaMonta) {
        Collections.sort(nopat, (a, b) -> b.getPisteluku() - a.getPisteluku());
        for (int i : nopat.stream()
                .map(Noppa::getPisteluku)
                .collect(Collectors.toList())) {
            
        }

    }

    public int pari(List<Noppa> nopat) {

    }
}
