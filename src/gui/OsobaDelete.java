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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spolupracejavisti.Databaze;
import spolupracejavisti.Osoba;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class OsobaDelete {

    private Stage primaryStage;
    private Text RC;
    private ComboBox<String> osobaRC;
    private Button dltbtn;
    private Databaze databaze;
    private HashMap<String, Osoba> mapaOsob;
    private ArrayList<Osoba> listOfOsobas;
    private Uzivatel uzivatel;

    public OsobaDelete(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        initListy();
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Smazat osobu");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        
        RC = new Text("Rodne cislo: ");

        osobaRC = new ComboBox<>();
        osobaRC.setPromptText("Vyberte RČ osoby, kterou chcete smazat");
        for (Osoba rc : listOfOsobas) {
            osobaRC.getItems().add(rc.getRodneCislo());
        }

        dltbtn = new Button("Smazat");
        dltbtn.setTooltip(new Tooltip("Smazat osobu"));
        dltbtn.setFont(Font.font("SanSerif", 15));
        dltbtn.setOnAction(e -> {

            if (uzivatel.maOpravneni(Uzivatel.Opravneni.SMAZAT_OSOBU) && databaze.deleteOsoba(osobaRC.getValue())) {
                databaze = new Databaze();
                initListy();
                for (Osoba rc : listOfOsobas) {
                    osobaRC.getItems().clear();
                    osobaRC.getItems().add(rc.getRodneCislo());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vymazani osoby");
                alert.setHeaderText("Osoba byla uspesne vymazana");
                alert.setContentText("Yeah!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Neco se stalo, nejde smazat osobu");
                alert.setContentText(":(");
                alert.showAndWait();
            }

        });

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(RC, osobaRC);
        vbox.getChildren().addAll(hbox, dltbtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public void initListy() {
        mapaOsob = databaze.getOsoby();
        listOfOsobas = new ArrayList<>();
        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOfOsobas.add(entry.getValue());
        }
    }

    public Stage getStage() {
        return primaryStage;
    }

}
