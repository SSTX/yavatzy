/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy;

import java.util.ArrayList;
import java.util.Scanner;
import ohha.yavatzy.sovelluslogiikka.Pelaaja;
import ohha.yavatzy.sovelluslogiikka.Peli;

/**
 *
 * @author ttiira
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Peli peli = new Peli(5, 6);
        peli.lisaaPelaaja("Jalmari");
        Pelaaja jalmari = peli.getPelaajat().get(0);
        int[] indeksit = {0, 1, 2, 3, 4};
        while (true) {
            System.out.println("Heitetään nopat:");
            peli.heitaNopat(indeksit);
            System.out.println(jalmari.nykyisetPisteluvut());
            System.out.println("Mihin kierrokseen kirjataan? (lopeta syötteellä 'lopeta')");
            String kierrosNimi = scan.nextLine();
            if (kierrosNimi.equals("lopeta")) {
                break;
            }
            peli.lisaaPisteet(kierrosNimi);
        }
        System.out.println("Tulostetaan pisteet:");
        System.out.println(peli.getPistelista().getPistelista().get(jalmari));
    }
}
