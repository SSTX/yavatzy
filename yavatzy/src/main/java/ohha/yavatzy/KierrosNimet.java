/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

import java.util.Arrays;

/**
 * Luokka joka säilöö yatzy-kierrosten nimet.
 * @author ttiira
 */
public class KierrosNimet {

    private static final String[] KIERROKSET = new String[] {
        "ykköset",
        "kakkoset",
        "kolmoset",
        "neloset",
        "vitoset",
        "kutoset",
        "bonus",
        "pari",
        "kaksi paria",
        "kolmoisluku",
        "neloisluku",
        "yatzy",
        "pieni suora",
        "iso suora",
        "täyskäsi",
        "sattuma"
    };

    /**
     * Palauttaa yatzy-kierrosten nimet taulukkona.
     * @return kierrosten nimet
     */
    public static String[] kierrosNimet() {
        return KIERROKSET;
    }

    /**
     * Palauttaa bonuksen laskemiseen hyväksyttävien kierrosten nimet.
     * @return bonuskierrosten nimet
     */
    public static String[] bonusKierrosNimet() {
        return Arrays.copyOfRange(KIERROKSET, 0, 6);
    }
}
