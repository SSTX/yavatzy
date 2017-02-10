/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.Objects;
import java.util.Random;

/**
 * Luokka simuloi nopan toimintaa. Sivujen määrä voi olla mikä tahansa nollaa
 * suurempi kokonaisluku
 */
public class Noppa {

    private Random rand;
    private int sivujenMaara;
    private int pisteluku;

    /**
     * Luodaan noppa annetuilla arvoilla.
     * @param sivujenMaara nopan sivujen määrä
     * @param rand satunnaisuuden toteuttava olio
     */
    public Noppa(int sivujenMaara, Random rand) {
        if (sivujenMaara <= 0) {
            // möbiuksen nauha sallitaan
            sivujenMaara = 1;
        }
        this.sivujenMaara = sivujenMaara;
        this.rand = rand;
        this.heita();
    }

    /**
     * Luodaan noppa oletussivumäärällä.
     *
     * @param rand pistelukujen arpomiseen tarvittava Random-olio
     */
    public Noppa(Random rand) {
        this(6, rand);
    }

    /**
     * Luodaan noppa oletussatunnaisuudella.
     *
     * @param sivujenMaara nopan sivujen määrä
     */
    public Noppa(int sivujenMaara) {
        this(sivujenMaara, new Random());
    }

    /**
     * Luodaan noppa oletusarvoilla.
     */
    public Noppa() {
        this(6, new Random());
    }

    public Random getRand() {
        return rand;
    }

    public int getSivujenMaara() {
        return sivujenMaara;
    }

    public int getPisteluku() {
        return this.pisteluku;
    }

    /**
     * Tallennetaan nopan pisteluvuksi uusi satunnainen kokonaisluku väliltä
     * 1..nopan sivujen määrä.
     *
     * @return saatu pisteluku
     */
    public int heita() {
        int tulos = this.getRand().nextInt(this.getSivujenMaara()) + 1;
        this.pisteluku = tulos;
        return tulos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.rand);
        hash = 97 * hash + this.sivujenMaara;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Noppa other = (Noppa) obj;
        if (this.sivujenMaara != other.sivujenMaara) {
            return false;
        }
        if (!Objects.equals(this.rand, other.rand)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.getPisteluku();
    }

}
