/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spolupracejavisti.Adresa;
import spolupracejavisti.Databaze;
import spolupracejavisti.Firma;
import spolupracejavisti.Osoba;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class FirmaView {

    private Stage primaryStage;
    private Text kontaktniOsoba, firmaICO, firmaNazev, firmaAdresa;
    private Button nextbtn, prvsButton, editButton;
    private Databaze databaze;
    private HashMap<String, Osoba> mapaOsob;
    private HashMap<Integer, Adresa> mapaAdresa;
    private HashMap<Integer, Firma> mapaFirem;
    private ArrayList<Adresa> listAdres;
    private ArrayList<Firma> listFirem;
    private ArrayList<Osoba> listOsob;
    private Uzivatel uzivatel;

    private int i = 0;

    public FirmaView(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;

        mapaAdresa = databaze.getAdresy();
        mapaOsob = databaze.getOsoby();
        mapaFirem = databaze.getFirmy();
        listAdres = new ArrayList<>();
        for (Map.Entry<Integer, Adresa> entry : mapaAdresa.entrySet()) {
            listAdres.add(entry.getValue());
        }
        listOsob = new ArrayList<>();
        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            listOsob.add(entry.getValue());
        }
        listFirem = new ArrayList<>();
        for (Map.Entry<Integer, Firma> entry : mapaFirem.entrySet()) {
            listFirem.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Firmy");

        System.out.println(listFirem.size());

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        String kontakniOsoba = null;

        for (Osoba osoba : listOsob) {
            if (osoba.getRodneCislo().equals(listFirem.get(i).getOsoba().getRodneCislo())) {
                kontakniOsoba = osoba.getRodneCislo() + " Jmeno: " + osoba.getJmeno();
            }
        }
        kontaktniOsoba = new Text("Kontakní osoba: " + kontakniOsoba);
        kontaktniOsoba.setFont(Font.font("SanSerif", 20));

        firmaICO = new Text("ICO firmy: " + listFirem.get(i).getICO());
        firmaICO.setFont(Font.font("SanSerif", 20));

        firmaNazev = new Text("Nazev firmy: " + listFirem.get(i).getNazev());
        firmaNazev.setFont(Font.font("SanSerif", 20));

        String adresaFirmy = null;
        for (Adresa adresa : listAdres) {
            if (adresa.equals(listFirem.get(i).getAdresa())) {
                adresaFirmy = "Adresa: " + adresa.getStat() + " " + adresa.getMesto() + " " + adresa.getUlice() + " " + adresa.getZip();
            }
        }
        firmaAdresa = new Text(adresaFirmy);
        firmaAdresa.setFont(Font.font("SanSerif", 20));

        nextbtn = new Button("next");
        nextbtn.setTooltip(new Tooltip("pristi firma"));
        nextbtn.setFont(Font.font("SanSerif", 15));
        nextbtn.setOnAction(e -> {
            if (i < listFirem.size() - 1) {
                i++;
                refreshStage();
            }
        });
        prvsButton = new Button("previous");
        prvsButton.setTooltip(new Tooltip("minula firma"));
        prvsButton.setFont(Font.font("SanSerif", 15));
        prvsButton.setOnAction(e -> {
            if (i > 0) {
                i--;
                refreshStage();
            }
        });
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_FIRMU)) {
            editButton = new Button("edit");
            editButton.setTooltip(new Tooltip("upravit firmu"));
            editButton.setFont(Font.font("SanSerif", 15));
            editButton.setOnAction(e -> {
                FirmaEdit firmaEdit = new FirmaEdit(databaze, uzivatel, listFirem.get(i), this);
                Stage stage = firmaEdit.getStage();
                stage.show();
            });
        }

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.getChildren().addAll(kontaktniOsoba, firmaICO, firmaNazev, firmaAdresa, nextbtn, prvsButton);
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_FIRMU)) {
            vbox.getChildren().add(editButton);
        }
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refreshStage() {
        String kontakniOsoba = null;

        for (Osoba osoba : listOsob) {
            if (osoba.getRodneCislo().equals(listFirem.get(i).getOsoba().getRodneCislo())) {
                kontakniOsoba = osoba.getRodneCislo() + " Jmeno: " + osoba.getJmeno();
            }
        }
        kontaktniOsoba.setText("Kontaktní osoba: " + kontakniOsoba);

        firmaICO.setText("ICO firmy: " + listFirem.get(i).getICO());

        firmaNazev.setText("Nazev firmy: " + listFirem.get(i).getNazev());

        String adresaFirmy = null;
        for (Adresa adresa : listAdres) {
            if (adresa.equals(listFirem.get(i).getAdresa())) {
                adresaFirmy = "Adresa: " + adresa.getStat() + " " + adresa.getMesto() + " " + adresa.getUlice() + " " + adresa.getZip();
            }
        }
        firmaAdresa.setText(adresaFirmy);

    }

}
