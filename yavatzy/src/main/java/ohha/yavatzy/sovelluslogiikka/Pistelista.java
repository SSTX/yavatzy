/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ttiira
 */

public class Pistelista {

    private Map<Pelaaja, Map<String, Integer>> pistelista;

    
    public Pistelista() {
        this.pistelista = new HashMap<>();
    }

    public Map<Pelaaja, Map<String, Integer>> getPistelista() {
        return pistelista;
    }

    public boolean lisaaPelaaja(Pelaaja pelaaja) {
        if (!this.getPistelista().containsKey(pelaaja)) {
            this.getPistelista().put(pelaaja, new HashMap<>());
            return true;
        }
        return false;
    }
    
    public boolean lisaaPisteet(Pelaaja pelaaja, String kierrosNimi, int pisteet) {
        Map<String, Integer> pelaajanPisteet = this.getPistelista().get(pelaaja);
        if (pelaajanPisteet != null && !pelaajanPisteet.containsKey(kierrosNimi)) {
            pelaajanPisteet.put(kierrosNimi, pisteet);
            return true;
        }
        return false;
    }
    
    public int pelaajanPisteetKierrokselta(Pelaaja pelaaja, String kierrosNimi) {
        if (this.getPistelista().containsKey(pelaaja)
                && this.getPistelista().get(pelaaja).containsKey(kierrosNimi)) {
            return this.getPistelista().get(pelaaja).get(kierrosNimi);
        }
        return 0;
    }
    
    public boolean bonus(Pelaaja pelaaja) {
        return false;
    }
}
