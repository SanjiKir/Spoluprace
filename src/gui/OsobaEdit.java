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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spolupracejavisti.Adresa;
import spolupracejavisti.Databaze;
import spolupracejavisti.Osoba;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class OsobaEdit {
     private Stage primaryStage;
    private TextField jmeno, rodneCislo, telefon, email;
    private Button savebtn;
    private Databaze databaze;
    private HashMap<String, Osoba> mapOfOsoby;
    private HashMap<Integer, Adresa> mapOfAdresy;
    private ArrayList<Osoba> listOfOsoby;
    private ArrayList<Adresa> listOfAdresy;
    private ComboBox<String> comboBoxAdresa;
    private Uzivatel uzivatel;
    private Osoba osoba;
    private OsobaView parentStage;

    public OsobaEdit(Databaze databaze, Uzivatel uzivatel, Osoba osoba, OsobaView parentStage) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        this.osoba = osoba;
        this.parentStage = parentStage;
        
        mapOfOsoby = databaze.getOsoby();
        mapOfAdresy = databaze.getAdresy();
        listOfOsoby = new ArrayList<>();
        for (Map.Entry<String, Osoba> entry : mapOfOsoby.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOfOsoby.add(entry.getValue());
        }
        listOfAdresy = new ArrayList<>();
        for (Map.Entry<Integer, Adresa> entry : mapOfAdresy.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOfAdresy.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Upravit osobu");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

       

        jmeno = new TextField();
        jmeno.setPromptText("Jmeno osoby");
        jmeno.setText(osoba.getJmeno());
        
        rodneCislo = new TextField();
        rodneCislo.setPromptText("Rodne cislo osoby");
        rodneCislo.setText(osoba.getRodneCislo());
        rodneCislo.setDisable(true);
        
        telefon = new TextField();
        telefon.setPromptText("Telefon osoby");
        telefon.setText(osoba.getTelefon());
        
        email = new TextField();
        email.setPromptText("E-mail osoby");
        email.setText(osoba.getEmail());
        
        comboBoxAdresa = new ComboBox();
        comboBoxAdresa.setPromptText("Adresa");
        
        for(Adresa adresa : listOfAdresy){
            comboBoxAdresa.getItems().add(adresa.getStat()+" " +adresa.getMesto()+" " +adresa.getUlice()+" " +adresa.getZip());
                    }
        comboBoxAdresa.setValue(osoba.getAdresa().getStat()+" " +osoba.getAdresa().getMesto()+" " +osoba.getAdresa().getUlice()+" " +osoba.getAdresa().getZip());
        
        
        savebtn = new Button("Uloz");
        savebtn.setTooltip(new Tooltip("Ulozit osoby do DB"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            int id = 0;
            for (Adresa adresa : listOfAdresy){ 
                    if((adresa.getStat()+" " +adresa.getMesto()+" " +adresa.getUlice()+" " +adresa.getZip()).equals(comboBoxAdresa.getValue())){
                        id = adresa.getId();
                    }
            }
            if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_OSOBU) && databaze.updateOsoba(osoba.getRodneCislo(), jmeno.getText(), telefon.getText(), email.getText(), id)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Úprava osoby");
                alert.setHeaderText("Osoba byla úspěšně upravena");
                alert.setContentText("Yeah!");
                alert.showAndWait();
                parentStage.refreshStage();
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Objevila se nejaka chybicka");
                alert.setContentText("Sorry");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(jmeno, rodneCislo, telefon, email, comboBoxAdresa, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }
}
