
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
import spolupracejavisti.Databaze;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class TypSpolupraceNew {

    private Stage primaryStage;
    private TextField name;
    private Button savebtn;
    private Databaze databaze;
    private HashMap<Integer, TypSpoluprace> mapOfTypuSpolupraci;
    private ArrayList<TypSpoluprace> listOfTypSpolupraci;
    private Uzivatel uzivatel;

    public TypSpolupraceNew(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        mapOfTypuSpolupraci = databaze.getTypySpoluprace();
        listOfTypSpolupraci = new ArrayList<>();
        for (Map.Entry<Integer, TypSpoluprace> entry : mapOfTypuSpolupraci.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOfTypSpolupraci.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Pridat novy typ spoluprace");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

       

        name = new TextField();
        name.setPromptText("Nazev typu spoluprace");

        savebtn = new Button("Uloz");
        savebtn.setTooltip(new Tooltip("Ulozit spoluprace do DB"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            int id = 0;
            for (TypSpoluprace typSpoluprace : listOfTypSpolupraci) {
                System.out.println(typSpoluprace.getNazev());
                if (typSpoluprace.getId() > id) {
                    id = typSpoluprace.getId();
                }
               id++;
            }
            if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_TYP_SPOLUPRACE) && databaze.insertNewTypSpoluprace(name.getText(), id)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pridani spoluprace");
                alert.setHeaderText("Spoluprace byla uspesne pridana");
                alert.setContentText("Yeah!");
                alert.showAndWait();
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
        vbox.getChildren().addAll(name, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

}
