/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovelluslogiikka;

import java.util.Random;

/**
 *
 * @author ttiira
 */
public class TestRandom extends Random {
    
    private int tila;
    public TestRandom() {
        this(0);
    }
    
    public TestRandom(int alkutila) {
        super();
        this.tila = alkutila;
    }
    @Override
    public int nextInt(int ylaraja) {
        this.tila = (this.tila + 1) % ylaraja;
        return this.tila;
    }
}
