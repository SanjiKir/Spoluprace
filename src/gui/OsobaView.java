/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author wooow
 */
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
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

public class OsobaView {

    private Stage primaryStage;
    private Text osobaRC, osobaJmeno, osobaTelefon, osobaEmail, osobaAdresa;
    private Button nextbtn, prvsButton, editButton;
    private Databaze databaze;
    private HashMap<String, Osoba> mapaOsob;
    private HashMap<Integer, Adresa> mapaAdres;
    private ArrayList<Osoba> listOsob;
    private int i = 0;
    private Uzivatel uzivatel;

    public OsobaView(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        mapaOsob = databaze.getOsoby();
        mapaAdres = databaze.getAdresy();
        
        listOsob = new ArrayList<>();
        for (Map.Entry<String, Osoba> entry : mapaOsob.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listOsob.add(entry.getValue());
        }
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("List osob");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);

        osobaRC = new Text("RC: " + listOsob.get(i).getRodneCislo());
        osobaRC.setFont(Font.font("SanSerif", 20));

        osobaJmeno = new Text("Jmeno: " + listOsob.get(i).getJmeno());
        osobaJmeno.setFont(Font.font("SanSerif", 20));

        osobaTelefon = new Text("Telefon: " + listOsob.get(i).getTelefon());
        osobaTelefon.setFont(Font.font("SanSerif", 20));

        osobaEmail = new Text("e-mail: " + listOsob.get(i).getEmail());
        osobaEmail.setFont(Font.font("SanSerif", 20));

        osobaAdresa = new Text("Adresa: " + listOsob.get(i).getAdresa().getStat() + " " + listOsob.get(i).getAdresa().getMesto() + " " + listOsob.get(i).getAdresa().getUlice());
        osobaAdresa.setFont(Font.font("SanSerif", 20));

        nextbtn = new Button("next");
        nextbtn.setTooltip(new Tooltip("pristi osoba"));
        nextbtn.setFont(Font.font("SanSerif", 15));
        nextbtn.setOnAction(e -> {
         //   System.out.println("I: " + i);
            if (i < listOsob.size() - 1) {
                i++;
                refreshStage();
            }
        });
        prvsButton = new Button("previous");
        prvsButton.setTooltip(new Tooltip("minula osoba"));
        prvsButton.setFont(Font.font("SanSerif", 15));
        prvsButton.setOnAction(e -> {
         //   System.out.println("I: " + i);
            if (i > 0) {
                i--;
                refreshStage();
            }
        });
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_OSOBU)) {
            editButton = new Button("edit");
            editButton.setTooltip(new Tooltip("upravit firmu"));
            editButton.setFont(Font.font("SanSerif", 15));
            editButton.setOnAction(e -> {
                OsobaEdit osobaEdit = new OsobaEdit(databaze, uzivatel, listOsob.get(i), this);
                Stage stage = osobaEdit.getStage();
                stage.show();
            });
        }

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.getChildren().addAll(osobaRC, osobaJmeno, osobaTelefon, osobaEmail, osobaAdresa, nextbtn, prvsButton);
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_OSOBU)) {
            vbox.getChildren().add(editButton);
        }
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refreshStage() {
   //     System.out.println("Size " + listOsob.size());
        osobaRC.setText("RČ: " + listOsob.get(i).getRodneCislo());
        osobaJmeno.setText("Jmeno: " + listOsob.get(i).getJmeno());
        osobaTelefon.setText("Telefon: " + listOsob.get(i).getTelefon());
        osobaEmail.setText("e-mail: " + listOsob.get(i).getEmail());
        osobaAdresa.setText("Adresa: " + listOsob.get(i).getAdresa().getStat() + " " + listOsob.get(i).getAdresa().getMesto() + " " + listOsob.get(i).getAdresa().getUlice());
    }

}
