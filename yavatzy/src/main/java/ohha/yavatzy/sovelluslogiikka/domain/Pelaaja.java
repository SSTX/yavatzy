/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.yavatzy.sovelluslogiikka.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Kuvaa yatzy-pelin pelaajaa.
 * Käytetään pääosin pistelistan hajautustaulun avaimena.
 */
public class Pelaaja {

    private String nimi;
    private int id;
    /**
     * Luodaan pelaaja annetulla nimellä.
     * @param nimi pelaajan nimi
     */
    public Pelaaja(String nimi) {
        if (nimi.isEmpty()) {
            nimi = "Anonyymi";
        }
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nimi);
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
        return this.getNimi();
    }


}
