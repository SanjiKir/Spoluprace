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
import spolupracejavisti.Osoba;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class SpolupraceView {
    
      private Stage primaryStage;
    private Text orgOsoba, realizOsoba, datumOd, datumDo, typSpoluprace;
    private Button nextbtn, prvsButton, editButton;
    private Databaze databaze;
    private HashMap<Integer, Spoluprace> mapOfSpolupraci;
    private HashMap<String, Osoba> mapaOsob;
    private HashMap<Integer, TypSpoluprace> mapaTypuSpoluprace;
    private ArrayList<Spoluprace> listOfSpolupraci;
    private ArrayList<Osoba> listOsob;
    private ArrayList<TypSpoluprace> listTypuSpoluprace;
    private int i = 0;
    private Uzivatel uzivatel;

    public SpolupraceView(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        mapOfSpolupraci = databaze.getSpoluprace();
        mapaTypuSpoluprace = databaze.getTypySpoluprace();
        mapaOsob = databaze.getOsoby();
        
        listOfSpolupraci = new ArrayList<>();
        for (Map.Entry<Integer, Spoluprace> entry : mapOfSpolupraci.entrySet()) {
            listOfSpolupraci.add(entry.getValue());
        }
        listOsob = new ArrayList<>();
        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            listOsob.add(entry.getValue());
        }
        listTypuSpoluprace = new ArrayList<>();
        for (Map.Entry<Integer, TypSpoluprace> entry : mapaTypuSpoluprace.entrySet()) {
            listTypuSpoluprace.add(entry.getValue());
        }
        
        
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Spoluprácí");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        String orgOsobaID = null;
        String realizOsobaId = null;
        for (Osoba osoba : listOsob){
            if(osoba.getRodneCislo().equals(listOfSpolupraci.get(i).getOrganizujiciOsoba().getRodneCislo())){
                orgOsobaID = osoba.getRodneCislo() + " Jmeno: " + osoba.getJmeno();
            }
            if (osoba.getRodneCislo().equals(listOfSpolupraci.get(i).getRealizujiciOsoba().getRodneCislo())){
                realizOsobaId = osoba.getRodneCislo()+ " Jmeno: " + osoba.getJmeno();
            }
        }
        orgOsoba = new Text("Org. osoba: " + orgOsobaID);
        orgOsoba.setFont(Font.font("SanSerif", 20));

        realizOsoba = new Text("Real. osoba: " + realizOsobaId);
        realizOsoba.setFont(Font.font("SanSerif", 20));

        datumOd = new Text("Datum zacatku: " + listOfSpolupraci.get(i).getDatumOd());
        datumOd.setFont(Font.font("SanSerif", 20));

        datumDo = new Text("Datum konce: " + listOfSpolupraci.get(i).getDatumDo());
        datumDo.setFont(Font.font("SanSerif", 20));
        String typSpoluprace1 = null;
        for (TypSpoluprace typSpoluprace : listTypuSpoluprace){
            if (typSpoluprace.getId() == listTypuSpoluprace.get(i).getId()){
                typSpoluprace1 = "Typ spoluprace: " + typSpoluprace.getId() + " " + typSpoluprace.getNazev();
            }
        }
        typSpoluprace = new Text(typSpoluprace1);
        typSpoluprace.setFont(Font.font("SanSerif", 20));

        nextbtn = new Button("next");
        nextbtn.setTooltip(new Tooltip("pristi spoluprace"));
        nextbtn.setFont(Font.font("SanSerif", 15));
        nextbtn.setOnAction(e -> {
            if (i < listOfSpolupraci.size() - 1) {
                i++;
                refreshStage();
            }
        });
        
        prvsButton = new Button("previous");
        prvsButton.setTooltip(new Tooltip("minula spolupraci"));
        prvsButton.setFont(Font.font("SanSerif", 15));
        prvsButton.setOnAction(e -> {
            if (i > 0) {
                i--;
                refreshStage();
            }
        });
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_SPOLUPRACI)) {
            editButton = new Button("edit");
            editButton.setTooltip(new Tooltip("upravit firmu"));
            editButton.setFont(Font.font("SanSerif", 15));
            editButton.setOnAction(e -> {
                SpolupraceEdit spolupraceEdit = new SpolupraceEdit(databaze, uzivatel, listOfSpolupraci.get(i), this);
                Stage stage = spolupraceEdit.getStage();
                stage.show();
            });
        }

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.getChildren().addAll(orgOsoba, realizOsoba, datumOd, datumDo, typSpoluprace, nextbtn, prvsButton);
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_SPOLUPRACI)) {
            vbox.getChildren().add(editButton);
        }
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refreshStage() {
           String orgOsobaID = null;
        String realizOsobaId = null;
        for (Osoba osoba : listOsob){
            if(osoba.getRodneCislo().equals(listOfSpolupraci.get(i).getOrganizujiciOsoba().getRodneCislo())){
                
                orgOsobaID = osoba.getRodneCislo() + " Jmeno: " + osoba.getJmeno();
            }
            if (osoba.getRodneCislo().equals(listOfSpolupraci.get(i).getRealizujiciOsoba().getRodneCislo())){
                realizOsobaId = osoba.getRodneCislo()+ " Jmeno: " + osoba.getJmeno();
            }
        }
        orgOsoba.setText("Org. osoba: " + orgOsobaID);


        realizOsoba.setText("Real. osoba: " + realizOsobaId);


        datumOd.setText("Datum zacatku: " + listOfSpolupraci.get(i).getDatumOd());


        datumDo.setText("Datum konce: " + listOfSpolupraci.get(i).getDatumDo());

        String typSpoluprace1 = null;
        for (TypSpoluprace typSpoluprace : listTypuSpoluprace){
            if (typSpoluprace.getId() == listTypuSpoluprace.get(i).getId()){
                typSpoluprace1 = "Typ spoluprace: " + typSpoluprace.getId() + " " +  typSpoluprace.getNazev();
            }
        }
        typSpoluprace.setText(typSpoluprace1);

    }

    
}
