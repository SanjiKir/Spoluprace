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
import spolupracejavisti.Firma;
import spolupracejavisti.Spoluprace;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class SpolupraceDelete {

    private Stage primaryStage;
    private Text ICO;
    private ComboBox<String> spolupraceId;
    private Button dltbtn;
    private Databaze databaze;
    private HashMap<Integer, Spoluprace> mapaSpolupraci;
    private ArrayList<Spoluprace> listSpolupraci;
    private Uzivatel uzivatel;

    public SpolupraceDelete(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        
        initListy();
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Smazat firmu");

        Group root = new Group();
        Scene scene = new Scene(root, 450, 400);
        
        ICO = new Text("ICO: ");

        spolupraceId = new ComboBox<>();
        spolupraceId.setPromptText("Vyberte ID spolupráce, kterou chcete smazat");
        for (Spoluprace spoluprace : listSpolupraci) {
            spolupraceId.getItems().add("" + spoluprace.getId());
        }

        dltbtn = new Button("Smazat");
        dltbtn.setTooltip(new Tooltip("Smazat osobu"));
        dltbtn.setFont(Font.font("SanSerif", 15));
        dltbtn.setOnAction(e -> {

            if (uzivatel.maOpravneni(Uzivatel.Opravneni.SMAZAT_SPOLUPRACI) && databaze.deleteSpoluprace(Integer.parseInt(spolupraceId.getValue()))) {
                initListy();
                for (Spoluprace id : listSpolupraci) {
                    spolupraceId.getItems().clear();
                    spolupraceId.getItems().add("" + id.getId());
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vymazani spoluprace");
                alert.setHeaderText("Spoluprace byla uspesne vymazana");
                alert.setContentText("Yeah!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Neco se stalo, nejde smazat spolupraci");
                alert.setContentText(":(");
                alert.showAndWait();
            }

        });

        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(ICO, spolupraceId);
        vbox.getChildren().addAll(hbox, dltbtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        // primaryStage.show();
    }

    public void initListy() {
        mapaSpolupraci = databaze.getSpoluprace();
        listSpolupraci = new ArrayList<>();
        for (Map.Entry<Integer, Spoluprace> entry : mapaSpolupraci.entrySet()) {
            //  System.out.println(entry.getKey() + " " + entry.getValue());
            listSpolupraci.add(entry.getValue());
        }
    }

    public Stage getStage() {
        return primaryStage;
    }

}
