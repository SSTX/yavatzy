/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ttiira
 */
public class Peli {

    private List<Pelaaja> pelaajat;
    private int vuoroNumero;
    private Pistelista pistelista;
    private int noppienMaara;
    private int nopanSivujenMaara;
    private int kierros;
    private int kierroksienKokonaismaara;
    private Pisteytyssaannot saannot;
    private int[] valitutNopat;

    public Peli(int noppienMaara, int nopanSivujenMaara) {
        this.kierros = 1;
        this.kierroksienKokonaismaara = 15;
        this.noppienMaara = noppienMaara;
        this.nopanSivujenMaara = nopanSivujenMaara;
        this.pelaajat = new ArrayList<>();
        this.pistelista = new Pistelista();
        this.saannot = new Pisteytyssaannot();
        this.valitutNopat = new int[noppienMaara];
    }

    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public Pistelista getPistelista() {
        return pistelista;
    }

    public int getNoppienMaara() {
        return noppienMaara;
    }

    public int getNopanSivujenMaara() {
        return nopanSivujenMaara;
    }

    public int getKierros() {
        return kierros;
    }

    public int getVuoroNumero() {
        return vuoroNumero;
    }

    public Pisteytyssaannot getSaannot() {
        return saannot;
    }

    public int[] valitutNopat() {
        return this.valitutNopat;
    }

    public void seuraavaVuoro() {
        if (!this.getPelaajat().isEmpty()) {
            this.vuoroNumero = (this.vuoroNumero + 1) % this.pelaajienMaara();
        }
    }

    public int pelaajienMaara() {
        return this.getPelaajat().size();
    }

    public boolean peliLoppu() {
        return this.getKierros() > this.kierroksienKokonaismaara;
    }

    public Pelaaja vuorossaOlevaPelaaja() {
        return this.getPelaajat().get(this.getVuoroNumero());
    }

    public void lisaaPelaaja(String nimi) {
        List<Noppa> nopat = new ArrayList<>();
        for (int i = 0; i < this.getNoppienMaara(); i++) {
            nopat.add(new Noppa(this.getNopanSivujenMaara()));
        }

        Pelaaja lisattava = new Pelaaja(nimi, nopat);
        if (!this.getPelaajat().contains(lisattava)) {
            this.getPelaajat().add(lisattava);
        }
        this.getPistelista().lisaaPelaaja(lisattava);
    }

    public boolean lisaaPisteet(String kierrosNimi) {
        int pisteet = this.getSaannot()
                .pisteyta(kierrosNimi, this.vuorossaOlevaPelaaja().getNopat());
        boolean onnistui = this.getPistelista()
                .lisaaPisteet(this.vuorossaOlevaPelaaja(), kierrosNimi, pisteet);
        if (onnistui) {
            this.seuraavaVuoro();
        }
        return onnistui;
    }

    public void heitaNopat(int[] indeksit) {
        this.vuorossaOlevaPelaaja().heitaNopat(indeksit);
    }

}
