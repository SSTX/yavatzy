package ohha.yavatzy.sovellusogiikka;

public class VuoroKirjanpito {
    private int vuoroNumero;
    private int kierrosNumero;
    private int kierrostenKokonaismaara;

    public VuoroKirjanpito(int kierrostenKokonaismaara) {
        this.kierrostenKokonaismaara = kierrostenKokonaismaara;
        this.vuoroNumero = 0;
        this.kierrosNumero = 0;
    }

    public int getVuoroNumero() {
        return vuoroNumero;
    }

    public int getKierrosNumero() {
        return kierrosNumero;
    }

    public int getKierrostenKokonaismaara() {
        return kierrostenKokonaismaara;
    }

    public boolean kierroksetKayty() {
        return this.kierrosNumero >= this.kierrostenKokonaismaara;
    }

    public void seuraavaVuoro() {

    }

    public Pelaaja vuorossaOlevaPelaaja() {

        return null;
    }

    public boolean lisaaPelaaja(Pelaaja pelaaja) {

        return false;
    }
}
