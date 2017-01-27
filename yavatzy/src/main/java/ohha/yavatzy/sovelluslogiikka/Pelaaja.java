/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka;

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
    
    public Pelaaja(String nimi) {
        List<Noppa> nopat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nopat.add(new Noppa());
        }
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

    public void heitaNopat(int[] noppienIndeksit) {
        for (int i : noppienIndeksit) {
            if (i >= 0 && i < this.getNopat().size()) {
                this.getNopat().get(i).heita();
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        return true;
    }


    
    @Override
    public String toString() {
        StringBuilder build = new StringBuilder(this.getNimi());
        build.append(": ");
        for (int i : this.nykyisetPisteluvut()) {
            build.append(i);
            build.append(" ");
        }
        build.delete(build.length() - 1, build.length());
        return build.toString();
    }

}
