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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spolupracejavisti.Adresa;
import spolupracejavisti.Databaze;
import spolupracejavisti.Osoba;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class FirmaNew {

    private Stage primaryStage;
    private TextField firmaICO, firmaNazev;
    private ComboBox<String> adresa;
    private ComboBox<String> kontaktniOsoba;
    private Button savebtn;
    private Databaze databaze;
    private HashMap<Integer, Adresa> mapaAdres;
    private HashMap<String, Osoba> mapaOsob;
    private ArrayList<Osoba> listOsob;
    private ArrayList<Adresa> listAdres;
    private Uzivatel uzivatel;

    public FirmaNew(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;

        mapaAdres = databaze.getAdresy();
        mapaOsob = databaze.getOsoby();

        listOsob = new ArrayList<>();
        listAdres = new ArrayList<>();

        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOsob.add(entry.getValue());
        }
        for (Map.Entry<Integer, Adresa> entry : mapaAdres.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listAdres.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Vložit firmu");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        adresa = new ComboBox();
        adresa.setPromptText("Adresa");

        firmaICO = new TextField();
        firmaICO.setPromptText("IČO Firmy");

        firmaNazev = new TextField();
        firmaNazev.setPromptText("Název firmy");

        for (Adresa adresa1 : listAdres) {
            adresa.getItems().add(adresa1.getStat() + " " + adresa1.getMesto() + " " + adresa1.getUlice() + " " + adresa1.getCp());
        }

        kontaktniOsoba = new ComboBox();
        kontaktniOsoba.setPromptText("Kontakní osoba");

        for (Osoba osoba : listOsob) {
            kontaktniOsoba.getItems().add(osoba.getJmeno() + " RČ: " + osoba.getRodneCislo());
        }

        savebtn = new Button("Ulož");
        savebtn.setTooltip(new Tooltip("Uložit firmu do DB"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {

            int adresaID = 0;
            for (Adresa adresa1 : listAdres) {
                if (adresa.getValue().equals(adresa1.getStat() + " " + adresa1.getMesto() + " " + adresa1.getUlice() + " " + adresa1.getCp())) {
                    adresaID = adresa1.getId();
                }
            }
            String rcKontaktniOsoby = null;

            for (Osoba osoba : listOsob) {
                if ((osoba.getJmeno() + " RČ: " + osoba.getRodneCislo()).equals(kontaktniOsoba.getValue())) {
                    rcKontaktniOsoby = osoba.getRodneCislo();
                }
            }

            //   int ID,  String datumOd, String datumDo, int typSpolupraceId, int organizujiciOsoba, int realizujiciOsoba
            if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_FIRMU) && databaze.insertNewFirma(adresaID, Integer.parseInt(firmaICO.getText()), firmaNazev.getText(), rcKontaktniOsoby)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Přidání firmy");
                alert.setHeaderText("Firma byla uspešně přidana");
                alert.setContentText("Yeah!");
                alert.showAndWait();
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chýba");
                alert.setHeaderText("Objevila se nejaká chybička");
                alert.setContentText("Sorry");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(adresa, firmaICO, firmaNazev, kontaktniOsoba, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

}
