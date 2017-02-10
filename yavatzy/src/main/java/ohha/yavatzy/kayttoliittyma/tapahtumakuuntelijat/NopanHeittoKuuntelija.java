package ohha.yavatzy.kayttoliittyma.tapahtumakuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import ohha.yavatzy.sovelluslogiikka.Peli;

public class NopanHeittoKuuntelija implements ActionListener {

    private Peli peli;
    private List<JToggleButton> nopanValintaNapit;
    private JButton heitaNopatNappi;

    /**
     * Luodaan tapahtumakuuntelija.
     *
     * @param peli peli, johon heitettävät nopat kuuluvat
     * @param heitaNopatNappi nappi, johon tämä kuuntelija liittyy
     * @param nopanValintaNapit heitettävien noppien valintaan käytettävät
     * napit, kun nopat heitetään, poistetaan valinta kaikista
     */
    public NopanHeittoKuuntelija(Peli peli, JButton heitaNopatNappi, List<JToggleButton> nopanValintaNapit) {
        this.peli = peli;
        this.nopanValintaNapit = nopanValintaNapit;
        this.heitaNopatNappi = heitaNopatNappi;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.heitaNopat();
        for (int i = 0; i < this.nopanValintaNapit.size(); i++) {
            JToggleButton nappi = this.nopanValintaNapit.get(i);
            nappi.setText(this.peli.getNopat().get(i).toString());
            nappi.setSelected(false);
        }
        if (!peli.voidaanHeittaa()) {
            this.heitaNopatNappi.setEnabled(false);
        }
        this.peli.getValitutNopat().clear();
    }
}
