/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha2017_3.yavatzy.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author ttiira
 */
public class Pelaaja {

    private List<Noppa> nopat;
    private String nimi;

    public Pelaaja(String nimi, List<Noppa> nopat) {
        if (nimi.isEmpty()) {
            nimi = "Anonyymi";
        }
        this.nimi = nimi;
        this.nopat = nopat;
    }

    public List<Noppa> getNopat() {
        return nopat;
    }

    public String getNimi() {
        return nimi;
    }

    public List<Integer> nykyisetPisteluvut() {
        return this.getNopat()
                .stream()
                .map(Noppa::getPisteluku)
                .collect(Collectors.toList());
    }
    
    public void heitaNopat(List<Integer> noppienIndeksit) {
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nopat);
        hash = 47 * hash + Objects.hashCode(this.nimi);
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
        final Pelaaja other = (Pelaaja) obj;
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.nopat, other.nopat)) {
            return false;
        }
        return true;
    }

    
}
