package com.example.harkka1;


import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class kiviPaperiSakset {
    /**
     * pelaajan pisteet.
     */
    private int pisteet;
    /**
     * Tietokoneen pisteet.
     */
    private int konePisteet;
    /**
     * Pelattujen kierrosten määrä.
     */
    private int kierros;
    /**
     * Pelin maksimikierrosmäärä.
     */
    private int maxKierros;
    /**
     * Pelaajan liike.
     */
    private jj Pelaaja1;
    /**
     * Tietokoneen liike.
     */
    private jj Tietokone1;
    /**
     * Valitsee satunnaisesti tietokoneen liikkeen.
     */
    private Random random;

    /**
     * Tallentaa annetun siirron tiedostoon "pisteet.txt", joka sisältää pelitilanteen tiedot kunkin kierroksen jälkeen.
     * @param move Pelaajan tekemä siirto.
     * @throws IOException Poikkeus tulee jos tallentaminen epäonnistuu.
     */
    public void tallennaSiirtoTiedostoon(String move) throws IOException {
        FileWriter fw = new FileWriter("pisteet.txt", true);
        fw.write("kierros: " + this.kierros + ", Pelaajan valinta: " + this.Pelaaja1.toString() + ", tietokoneen valinta: " + this.Tietokone1.toString() + "\n");
        fw.write("Pelaajan pisteet: " + this.getPisteet() + " ja tietokoneen pisteet: " +this.getKonePisteet() +"\n");
        fw.write("\n\n\n");
        fw.close();

    }


    /**
     * Luokan konstruktori. joka luo uuden pelin.
     *
     * @param maxKierros pelattavien kierrosten eninmäismäärä.
     */
    public kiviPaperiSakset(int maxKierros) {
        this.pisteet = 0;
        this.konePisteet = 0;
        this.kierros = 0;
        this.maxKierros = maxKierros;
        this.random = new Random();
    }

    /**
     * Suorittaa aina yhden pelikierroksen.
     *
     * @param pm liike joka voi olla joko kivi , paperi tai sakset.
     */
    public void Kierros(String pm) {
        this.kierros++;
        this.Pelaaja1 = jj.Lii(pm);
        this.Tietokone1 = jj.liike();

        if (this.Pelaaja1 == this.Tietokone1) {
            return;
        } else if (this.Pelaaja1.Voittaa(this.Tietokone1)) {
            this.pisteet++;
        } else {
            this.konePisteet++;
        }
    }

    /**
     * tarkistaa onko peli loppunut.
     *
     * @return true jos peli loppui, muuten aina false.
     */
    public boolean peliLoppu() {
        return this.kierros >= this.maxKierros;
    }

    /**
     * palauttaa pelaajan pisteet.
     *
     * @return pelaajan pisteet.
     */
    public int getPisteet() {
        return this.pisteet;
    }

    /**
     * palauttaa tietokoneen pisteet.
     *
     * @return tietokoneen pisteet.
     */
    public int getKonePisteet() {
        return this.konePisteet;
    }

    /**
     * palauttaa kierrokset.
     *
     * @return pelatut kieerrokset.
     */
    public int getKierros() {
        return this.kierros;
    }

    /**
     * palauttaa maksimikierrokset.
     *
     * @return palauttaa maksimikierrokset.
     */
    public int getMaxKierros() {
        return this.maxKierros;
    }

    /**
     * @return Palauttaa voittajan joka on pelaaja, tietokone tai null eli tasapelin.
     */
    public String Voittaja() {
        if (this.pisteet > this.konePisteet) {
            return "Pelaaja";
        } else if (this.pisteet < this.konePisteet) {
            return "Tietokone";
        } else {
            return null;
        }
    }

    /**
     * Enum jj sisältää mahdolliset peliliikkeet.
     * Lii-metodi palauttaa syötteenä annetun merkin vastaavan liikkeen.
     * Liike-metodi antaa satunnaisen peliliikkeen.
     * Voittaa-metodi tarkistaa mikä liike voitti.
     */
    private enum jj {
        KIVI, PAPERI, SAKSET;

        /**
         * Palauttaa annetun merkin vastaavan liikkeen.
         *
         * @param s syöte merkkijonona.
         * @return peliliike.
         * antaa IllegalArgumentException jos tulee virhe.
         */

        public static jj Lii(String s) {
            switch (s.toLowerCase()) {
                case "kivi":
                    return KIVI;
                case "paperi":
                    return PAPERI;
                case "sakset":
                    return SAKSET;
                default:
                    throw new IllegalArgumentException("Virhe");
            }
        }

        /**
         * Arpoo liikkeen.
         *
         * @return liike.
         */
        public static jj liike() {
            return values()[new Random().nextInt(values().length)];
        }

        /**
         * tarkistaa mikä liike voitti.
         *
         * @param toinen peliliike.
         * @return true jos liike voitti muuten false.
         */
        public boolean Voittaa(jj toinen) {
            switch (this) {
                case KIVI:
                    return toinen == SAKSET;
                case PAPERI:
                    return toinen == KIVI;
                case SAKSET:
                    return toinen == PAPERI;
                default:
                    return false;
            }
        }
    }
}





