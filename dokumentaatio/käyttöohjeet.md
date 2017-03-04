# Pelaajien lisääminen
Ohjelman käynnistyessä tulee ensimmäiseksi näkyviin pelaajien lisäys -ruutu. Kirjoita tekstikenttään pelaajan nimi ja paina enter-näppäintä lisätäksesi pelaajan. Pelissä ei voi olla samannimisiä pelaajia. Kun olet lisännyt haluamasi määrän pelaajia tai pelaajien maksimimäärä on saavutettu, paina Aloita peli -nappia.

# Pelaaminen
Pelaajien lisäämisen päätyttyä aukeaa varsinainen peli-ikkuna. Yläosassa näkyy pistelista, ja alaosassa nopat sekä niihin liittyvät toiminnot.

## Pistelista
Pistelistassa vasemmassa sarakkeessa on yatzy-kierrosten nimet. Pelaajien nimet ovat näkyvillä ylärivissä. Näin määriteltyjen rivien ja sarakkeiden leikkauskohdissa ovat kyseisen pelaajan pisteet kyseiseltä kierrokselta. Pistelistan napit aktivoituvat pelaajien vuoron mukaan. Niiden kierrosten kohdalla, joihin vuorossa oleva pelaaja ei ole merkinnyt pisteitä, näytetään nykyisten noppien sille kierrokselle sallimat pisteet.  Klikkaamalla nappia asetat pisteet napin esittämälle kierrokselle ja pelaajalle. Tämän jälkeen vuoro siirtyy seuraavalle pelaajalle.

### Bonus
Jos pelaaja saa "yläkerrasta" (bonus-kierroksen yläpuolella olevat kierrokset) yhteensä 63 tai enemmän pistettä, hän saa bonuksen. Bonuksen arvo on 50 pistettä. Tällöin bonus-kierroksen kohdalla näytetään 50. Jos pelaaja ei ole saavuttanut bonusta, näytetään pelaajan siihen vielä tarvitsema pistemäärä suluissa, esim (63) pelin alussa.

## Nopat
Pelin alkaessa tai vuoron vaihtuessa noppia heitetään automaattisesti yhden kerran. Tämän jälkeen jokaisella pelaajalla on vuorollaan mahdollisuus kahdesti heittää haluamiansa noppia. Tämä tapahtuu klikkaamalla niitä noppia, jotka haluaa heittää, ja painamalla sitten Heitä nopat -nappia. Kun heittojen maksimimäärä saavutetaan, napit menevät pois päältä ja pelaajan täytyy asettaa pisteet jollekin kierrokselle.

# Pelin loppu
Kun kaikki pelaajat ovat asettaneet pisteet kaikille kierroksille, peli päättyy. Uusi peli -nappia painamalla voi aloittaa uuden pelin samoilla pelaajilla.
