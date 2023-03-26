package com.example.harkka1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Kivi extends Application {

    private kiviPaperiSakset Peli;

    /**
     * Näyttää pelaajan pisteet.
     */
    private Label pelaajanPisteet;
    /**
     * näyttää tietokoneen pisteet.
     */
    private Label tietokonePisteet;
    /**
     * Näyttää kierroksen
     */
    private Label Kierros;
    /**
     * näyttää voittajan
     */
    private Label Voittaja;
    /**
     * Kivi nappi.
     */
    private Button Kivi;
    /**
     * Paperi nappi.
     */
    private Button Paperi;

    /**
     * Sakset nappi.
     */
    private Button Sakset;

    /**
     * Käynnistää sovelluksen aloittamalla uuden pelin.
     * @param ikkuna on sovelluksen ikkuna, johon asetetaan käyttöliittymä.
     * @throws Exception antaa poikkeuksen, jos käynnistäminen epäonnistuu.
     */
    @Override
    public void start(Stage ikkuna) throws Exception {
        this.Peli = new kiviPaperiSakset(5);

        Label playerLabel = new Label("Pelaaja:");
        this.pelaajanPisteet = new Label("0");
        HBox pelaajaPis = new HBox(5, playerLabel, this.pelaajanPisteet);
        pelaajaPis.setAlignment(Pos.CENTER);

        Label computerLabel = new Label("Tietokone:");
        this.tietokonePisteet = new Label("0");
        HBox tietokonePis = new HBox(5, computerLabel, this.tietokonePisteet);
        tietokonePis.setAlignment(Pos.CENTER);

        Label kierrosOtsikko = new Label("Kierros:");
        this.Kierros = new Label("1/5");
        HBox Kierros = new HBox(5, kierrosOtsikko, this.Kierros);
        Kierros.setAlignment(Pos.CENTER);

        this.Voittaja = new Label("");
        HBox voittoNakyma = new HBox(this.Voittaja);
        voittoNakyma.setAlignment(Pos.CENTER);
        /**
         * Tehdään napit.
         */
        this.Kivi = new Button("Kivi");
        this.Sakset = new Button("Sakset");
        this.Paperi = new Button("Paperi");
        /**
        * Asetetaan kivi napille toiminto.
        */
        this.Kivi.setOnAction(event -> {
            try {
                playMove("kivi");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /**
         * Asetetaan paperi napille toiminto.
         */
        this.Paperi.setOnAction(event -> {
            try {
                playMove("paperi");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /**
         * Asetetaan sakset napille toiminto.
         */
        this.Sakset.setOnAction(event -> {
            try {
                playMove("sakset");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /**
         * Luo näkymän pelille.
         */
        VBox Nappi = new VBox(10, this.Kivi, this.Paperi, this.Sakset);
        Nappi.setAlignment(Pos.CENTER);

        VBox paneeli = new VBox(20, pelaajaPis, tietokonePis, Kierros, voittoNakyma, Nappi);
        paneeli.setPadding(new Insets(20));
        paneeli.setAlignment(Pos.CENTER);

        Scene kehys = new Scene(paneeli,300,300);
        ikkuna.setScene(kehys);
        ikkuna.setTitle("Kivi Paperi Sakset");
        ikkuna.show();
    }

    /**
     * Metodi suorittaa pelaajan siirron, tallentaa sen tiedostoon ja päivittää pelinäkymän.
     * @param move pelaajan siirto.
     * @throws IOException jos tallennus epäonnistuu.
     */
    private void playMove(String move) throws IOException {
        this.Peli.Kierros(move);
        Peli.tallennaSiirtoTiedostoon(move);
        this.uusiKuva();


        if (this.Peli.peliLoppu()) {
            this.Kivi.setDisable(true);
            this.Paperi.setDisable(true);
            this.Sakset.setDisable(true);

            String vv = this.Peli.Voittaja();
            if (vv == null) {
                this.Voittaja.setText("Tasapeli!");
            } else {
                this.Voittaja.setText(vv + " voitti!");
            }
        }
    }

    /**
     * päivittää pelinäkymän uusimmat tiedot.
     */
    private void uusiKuva() {
        this.pelaajanPisteet.setText(Integer.toString(this.Peli.getPisteet()));
        this.tietokonePisteet.setText(Integer.toString(this.Peli.getKonePisteet()));
        this.Kierros.setText(this.Peli.getKierros() + "/" + this.Peli.getMaxKierros());
    }

    public static void main(String[] args) {
    launch(args);

    }
}
