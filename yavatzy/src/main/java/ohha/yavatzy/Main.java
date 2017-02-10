/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

import javax.swing.SwingUtilities;
import ohha.yavatzy.kayttoliittyma.Kayttoliittyma;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 * Pääohjelma. Käynnistää yatzy-pelin.
 */
public class Main {

    /**
     * main-metodi. Käynnistää ohjelman.
     *
     * @param args Ohjelmalle ulkopuolelta annetut argumentit
     */
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        Peli peli = new Peli();
//        peli.lisaaPelaaja("Jalmari");
//        Pelaaja jalmari = peli.getPelaajat().get(0);
//        int[] indeksit = {0, 1, 2, 3, 4};
//        while (true) {
//            System.out.println("Heitetään nopat:");
//            peli.getNopat().stream().forEach(peli::valitseNoppa);
//            peli.heitaNopat();
//            System.out.println(peli.nykyisetPisteluvut());
//            System.out.println("Mihin kierrokseen kirjataan? (esim. pari, kaksi paria, kolmoisluku, iso suora. Lopeta syötteellä 'lopeta')");
//            String kierrosNimi = scan.nextLine();
//            if (kierrosNimi.equals("lopeta")) {
//                break;
//            }
//            peli.lisaaPisteet(kierrosNimi);
//        }
//        System.out.println("Tulostetaan pisteet:");
//        System.out.println(peli.getPistelista().getPistelista().get(jalmari));
        Peli peli = new Peli();
        peli.lisaaPelaaja("Jalmari");
        Kayttoliittyma kayttis = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kayttis);
    }
}
