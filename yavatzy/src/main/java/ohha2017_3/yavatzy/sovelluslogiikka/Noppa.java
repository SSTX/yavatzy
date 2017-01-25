/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha2017_3.yavatzy.sovelluslogiikka;

import java.util.Random;

/**
 *
 * @author ttiira
 */
public class Noppa {

    private Random rand;
    private int sivujenMaara;
    private int pisteluku;

    public Noppa(int sivujenMaara, Random rand) {
        if (sivujenMaara < 1) {
            // möbiuksen nauha sallitaan
            sivujenMaara = 1;
        }
        this.sivujenMaara = sivujenMaara;
        this.rand = rand;
        this.heita();
    }
    
    public Noppa(Random rand) {
        this(6, rand);
    }
    
    public Noppa(int sivujenMaara) {
        this(sivujenMaara, new Random());
    }
    
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

    public int heita() {
        int tulos = this.getRand().nextInt(this.getSivujenMaara()) + 1;
        this.pisteluku = tulos;
        return tulos;
    }

    @Override
    public int hashCode() {
        return this.getRand().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Noppa noppa = (Noppa) obj;
        return this.getRand().equals(noppa.getRand());
    }

}
