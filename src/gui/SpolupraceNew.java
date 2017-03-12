/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spolupracejavisti.Databaze;
import spolupracejavisti.Osoba;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class SpolupraceNew {

    private Stage primaryStage;

    private DatePicker datumOd;
    private DatePicker datumDo;
    private ComboBox<String> typSpolupraceId;
    private ComboBox<String> organizujiciOsoba;
    private ComboBox<String> realizujiciOsoba;

    private Button savebtn;
    private Databaze databaze;
    private HashMap<Integer, Spoluprace> mapOfSpolupraci;
    private HashMap<String, Osoba> mapaOsob;
    private HashMap<Integer, TypSpoluprace> mapaTypuSpoluprace;
    private ArrayList<Spoluprace> listOfSpolupraci;
    private ArrayList<Osoba> listOsob;
    private ArrayList<TypSpoluprace> listTypuSpoluprace;
    private Uzivatel uzivatel;

    public SpolupraceNew(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        mapOfSpolupraci = databaze.getSpoluprace();
        mapaOsob = databaze.getOsoby();
        mapaTypuSpoluprace = databaze.getTypySpoluprace();
        listOfSpolupraci = new ArrayList<>();
        listOsob = new ArrayList<>();
        listTypuSpoluprace = new ArrayList<>();
        for (Map.Entry<Integer, Spoluprace> entry : mapOfSpolupraci.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOfSpolupraci.add(entry.getValue());
        }
        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOsob.add(entry.getValue());
        }
        for (Map.Entry<Integer, TypSpoluprace> entry : mapaTypuSpoluprace.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listTypuSpoluprace.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Vložit spolupráci");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        

        datumOd = new DatePicker();
        datumOd.setPromptText("Datum od");

        datumDo = new DatePicker();
        datumDo.setPromptText("Datum od");

        typSpolupraceId = new ComboBox();
        typSpolupraceId.setPromptText("Typ spolupráce");

        for (TypSpoluprace typSpoluprace : listTypuSpoluprace) {
            typSpolupraceId.getItems().add(typSpoluprace.getId() + " " + typSpoluprace.getNazev() );
        }

        organizujiciOsoba = new ComboBox();
        organizujiciOsoba.setPromptText("Organizující osoba");

        for (Osoba osoba : listOsob) {
            organizujiciOsoba.getItems().add(osoba.getJmeno() + "RC:" +osoba.getRodneCislo());
        }

        realizujiciOsoba = new ComboBox();
        realizujiciOsoba.setPromptText("Realizující osoba");

        for (Osoba osoba : listOsob) {
            realizujiciOsoba.getItems().add(osoba.getJmeno()+ "RC:"+osoba.getRodneCislo());
        }

        savebtn = new Button("Ulož");
        savebtn.setTooltip(new Tooltip("Uložit spolupráce do DB"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            int id = 0;
            for (Spoluprace spoluprace : listOfSpolupraci) {
                if (spoluprace.getId() > id) {
                    id = spoluprace.getId();
                }
                id++;
            }
            
            int typSpolupraceID = 0;
            for (TypSpoluprace typSpoluprace : listTypuSpoluprace){
                if (typSpolupraceId.getValue().equals(typSpoluprace.getId() + " " + typSpoluprace.getNazev())){
                    typSpolupraceID = typSpoluprace.getId();
                }
            }
            String rcOrgOsoby = null;
            String rcRealizOsoby = null;
            for (Osoba osoba : listOsob){
                if ((osoba.getJmeno()+ "RC:"+osoba.getRodneCislo()).equals(organizujiciOsoba.getValue())){
                    rcOrgOsoby = osoba.getRodneCislo();
            }
            if ((osoba.getJmeno()+ "RC:"+osoba.getRodneCislo()).equals(realizujiciOsoba.getValue())){
                rcRealizOsoby = osoba.getRodneCislo();
            }
            }
            
         //   int ID,  String datumOd, String datumDo, int typSpolupraceId, int organizujiciOsoba, int realizujiciOsoba
            if (databaze.insertNewSpoluprace(id, datumOd.getValue().toString(), datumDo.getValue().toString(), typSpolupraceID, rcOrgOsoby, rcRealizOsoby)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Přidání spolupráce");
                alert.setHeaderText("Spolupráce byla uspešně přidana");
                alert.setContentText("Yeah!");
                alert.showAndWait();
                primaryStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chýba");
                alert.setHeaderText("Objevila se nejaká chýbička");
                alert.setContentText("Sorry");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(datumOd, datumDo, typSpolupraceId, organizujiciOsoba, realizujiciOsoba, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

}
