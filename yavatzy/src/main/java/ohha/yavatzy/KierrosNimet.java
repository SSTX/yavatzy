/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

/**
 *
 * @author ttiira
 */
public class KierrosNimet {

    private static final String[] kierrokset
            = new String[]{
                "ykköset",
                "kakkoset",
                "kolmoset",
                "neloset",
                "vitoset",
                "kutoset",
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

    public static String[] kierrosNimet() {
        return kierrokset;
    }
}
