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
 * Laskee pistemäärän, jonka nopat antavat tietylle yatzy-kierrokselle. Tarjoaa
 * yhden julkisen metodin, joka valitsee oikeat säännöt kierroksen nimen
 * (merkkijono) perusteella.
 */
public class Pisteytyssaannot {

    private int pieniSuoraPisteet;
    private int isoSuoraPisteet;
    private int yatzyPisteet;

    /**
     * Hajautustaulun avaimet ovat yatzy-kierroksen nimiä. Arvot ovat
     * funktioita, jotka liittävät listaan noppia kokonaislukuarvon eli sen
     * pistemäärän, jonka nopat antavat avaimen määrämälle kierrokselle.
     */
    private Map<String, Function<List<Noppa>, Integer>> metodit;

    /**
     * Luodaan pisteytyssäännöt. Lisätään merkkijono-funktio -parit
     * hajautustauluun.
     */
    public Pisteytyssaannot() {
        this.pieniSuoraPisteet = 15;
        this.isoSuoraPisteet = 20;
        this.yatzyPisteet = 50;
        this.metodit = new HashMap<>();
        // lisätään metodit tauluun
        metodit.put("ykköset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 1);
        });
        metodit.put("kakkoset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 2);
        });
        metodit.put("kolmoset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 3);
        });
        metodit.put("neloset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 4);
        });
        metodit.put("vitoset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 5);
        });
        metodit.put("kutoset", (nopat) -> {
            return this.laskePistelukujenSumma(nopat, 6);
        });
        metodit.put("pari", (nopat) -> {
            return this.montaSamaa(nopat, 2);
        });
        metodit.put("kaksi paria", (nopat) -> {
            return this.kaksiParia(nopat);
        });
        metodit.put("kolmoisluku", (nopat) -> {
            return this.montaSamaa(nopat, 3);
        });
        metodit.put("neloisluku", (nopat) -> {
            return this.montaSamaa(nopat, 4);
        });
        metodit.put("pieni suora", (nopat) -> {
            return this.pieniSuora(nopat);
        });
        metodit.put("iso suora", (nopat) -> {
            return this.isoSuora(nopat);
        });
        metodit.put("täyskäsi", (nopat) -> {
            return this.tayskasi(nopat);
        });
        metodit.put("sattuma", (nopat) -> {
            return this.sattuma(nopat);
        });
        metodit.put("yatzy", (nopat) -> {
            return this.yatzy(nopat);
        });
    }

    /**
     * Pisteyttää parametrina tulevat nopat annetun kierroksen sääntöjen mukaan.
     * @param kierrosNimi Merkkijono, joka ilmoittaa yatzy-kierroksen nimen.
     * Pisteet lasketaan merkkijonoon liitetyt metodin avulla.
     * @param nopat Noppa-oliot, joiden pistelukujen perusteella pisteet
     * lasketaan.
     * @return Noppien pistelukujen sallima suurin pistemäärä halutulle
     * kierrokselle. Jos kierrokselle ei ole pisteytyssääntöjä, palautetaan
     * nolla.
     */
    public int pisteyta(String kierrosNimi, List<Noppa> nopat) {
        if (this.metodit.get(kierrosNimi) != null) {
            return this.metodit.get(kierrosNimi).apply(nopat);
        }
        // halutun nimistä kierrosta ei ole
        return 0;
    }

    private List<Integer> muunnaPisteluvuiksi(List<Noppa> nopat) {
        return nopat.stream().map(Noppa::getPisteluku).collect(Collectors.toList());
    }

    private boolean kaikkiEri(List<Noppa> nopat) {
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        for (int i : pisteluvuittain.keySet()) {
            if (pisteluvuittain.get(i).size() > 1) {
                return false;
            }
        }
        return true;
    }

    private int montaSamaa(List<Noppa> nopat, int kuinkaMonta) {
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

    private int laskePistelukujenSumma(List<Noppa> nopat, int haluttuPisteluku) {
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

    private int kaksiParia(List<Noppa> nopat) {
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

    private int yatzy(List<Noppa> nopat) {
        if (this.montaSamaa(nopat, 5) > 0) {
            return this.yatzyPisteet;
        }
        return 0;
    }

    private int tayskasi(List<Noppa> nopat) {
        Map<Integer, List<Noppa>> pisteluvuittain = this.ryhmittelePisteluvunMukaan(nopat);
        int tulos = 0;
        for (int pisteluku : pisteluvuittain.keySet()) {
            List<Noppa> valitut = pisteluvuittain.get(pisteluku);
            switch (valitut.size()) {
                case 2:
                    tulos += this.montaSamaa(valitut, 2);
                    break;
                case 3:
                    tulos += this.montaSamaa(valitut, 3);
                    break;
                default:
                    //jos on samaa pistelukua muu määrä kuin 2 tai 3, ei ole täyskäsi
                    return 0;
            }
        }
        return tulos;
    }

    private int pieniSuora(List<Noppa> nopat) {
        if (this.kaikkiEri(nopat) && this.muunnaPisteluvuiksi(nopat).contains(1)) {
            return this.pieniSuoraPisteet;
        }
        return 0;
    }

    private int isoSuora(List<Noppa> nopat) {
        if (this.kaikkiEri(nopat) && this.muunnaPisteluvuiksi(nopat).contains(6)) {
            return this.isoSuoraPisteet;
        }
        return 0;
    }

    private int sattuma(List<Noppa> nopat) {
        return nopat.stream().mapToInt(Noppa::getPisteluku).sum();
    }
}
