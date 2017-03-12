

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
import spolupracejavisti.Databaze;
import spolupracejavisti.TypSpoluprace;
import spolupracejavisti.Uzivatel;

public class TypSpolupraceView {

    private Stage primaryStage;
    private Text nazevSpoluprace, id;
    private Button nextbtn, prvsButton, editButton;
    private Databaze databaze;
    private HashMap<Integer, TypSpoluprace> mapOfTypuSpolupraci;
    private ArrayList<TypSpoluprace> listOfTypSpolupraci;
    private int i = 0;
    private Uzivatel uzivatel;

    public TypSpolupraceView(Databaze databaze, Uzivatel uzivatel) {
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
        primaryStage.setTitle("Typy spolupraci");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);

        nazevSpoluprace = new Text("Nazev: " + listOfTypSpolupraci.get(i).getNazev());
        nazevSpoluprace.setFont(Font.font("SanSerif", 20));

        id = new Text("ID: " + listOfTypSpolupraci.get(i).getId());
        id.setFont(Font.font("SanSerif", 20));

        nextbtn = new Button("next");
        nextbtn.setTooltip(new Tooltip("pristi typ spoluprace"));
        nextbtn.setFont(Font.font("SanSerif", 15));
        nextbtn.setOnAction(e -> {
            if (i < listOfTypSpolupraci.size() - 1) {
                i++;
                refreshStage();
            }
        });
        prvsButton = new Button("previous");
        prvsButton.setTooltip(new Tooltip("minuly typ spoluprace"));
        prvsButton.setFont(Font.font("SanSerif", 15));
        prvsButton.setOnAction(e -> {
            if (i > 0) {
                i--;
                refreshStage();
            }
        });
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_TYP_SPOLUPRACE)) {
            editButton = new Button("edit");
            editButton.setTooltip(new Tooltip("upravit typ spolupráce"));
            editButton.setFont(Font.font("SanSerif", 15));
            editButton.setOnAction(e -> {
                TypSpolupraceEdit typSpolupraceEdit = new TypSpolupraceEdit(databaze, uzivatel, listOfTypSpolupraci.get(i), this);
                Stage stage = typSpolupraceEdit.getStage();
                stage.show();
            });
        }

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.getChildren().addAll(nazevSpoluprace, id, nextbtn, prvsButton);
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.EDITOVAT_TYP_SPOLUPRACE)) {
            vbox.getChildren().add(editButton);
        }
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void refreshStage() {
        nazevSpoluprace.setText("Nazev: " + listOfTypSpolupraci.get(i).getNazev());
        id.setText("ID: " + listOfTypSpolupraci.get(i).getId());
    }

}
