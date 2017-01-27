/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ttiira
 */
public class Peli {

    private List<Pelaaja> pelaajat;
    private Pistelista pistelista;
    private int noppienMaara;
    private int nopanSivujenMaara;
    private int kierros;
    private int kierroksienKokonaismaara;

    public Peli(int noppienMaara, int nopanSivujenMaara) {
        this.kierros = 1;
        this.kierroksienKokonaismaara = 15;
        this.noppienMaara = noppienMaara;
        this.nopanSivujenMaara = nopanSivujenMaara;
        this.pelaajat = new ArrayList<>();
        this.pistelista = new Pistelista();
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

    public boolean peliLoppu() {
        return this.getKierros() > this.kierroksienKokonaismaara;
    }
    
    public boolean lisaaPisteet(Pelaaja pelaaja, String kierrosNimi, int pisteet) {
        return this.getPistelista().lisaaPisteet(pelaaja, kierrosNimi, pisteet);
    }
    
    public String heitaNopat(Pelaaja pelaaja, int[] indeksit) {
        pelaaja.heitaNopat(indeksit);
        return pelaaja.nykyisetPisteluvut().toString();
    }

}
