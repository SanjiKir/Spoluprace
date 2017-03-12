/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import spolupracejavisti.Databaze;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class SignUpWindow {

    private Stage primaryStage;
    private TextField login, password, RC;
    private Button savebtn;
    private Databaze databaze;

    public SignUpWindow(Databaze databaze) {
        this.databaze = databaze;
        init();
    }

    public void init() {
        primaryStage = new Stage();
        primaryStage.setTitle("Registration window");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        login = new TextField();
        login.setTooltip(new Tooltip("Enter your login"));
        login.setFont(Font.font("SanSerif", 15));
        login.setPromptText("Login");
        login.setMaxWidth(200);

        password = new TextField();
        password.setTooltip(new Tooltip("Enter password"));
        password.setFont(Font.font("SanSerif", 15));
        password.setPromptText("Heslo");
        password.setMaxWidth(200);

        RC = new TextField();
        RC.setTooltip(new Tooltip("Osoba s timto rodnym cislem musi byt v databazi"));
        RC.setFont(Font.font("SanSerif", 15));
        RC.setPromptText("Rodne cislo");
        RC.setMaxWidth(200);

        savebtn = new Button("Register");
        savebtn.setTooltip(new Tooltip("Register"));
        savebtn.setFont(Font.font("SanSerif", 15));
        savebtn.setOnAction(e -> {
            if (login.getText().equals("") || password.getText().equals("") || RC.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Neuplna data");
                alert.setHeaderText("Neco chybi, pridej vsechna data");

                alert.showAndWait();
            }

            Uzivatel uzivatel = new Uzivatel(databaze.getOsoba(RC.getText()), login.getText().toLowerCase(), password.getText(), Uzivatel.TypUzivatele.UZIVATEL);
            clearFields();
            if (databaze.signUpAttempt(uzivatel)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration");
                alert.setHeaderText("Byl jste zaregistrován!");
                alert.setContentText("Yeah!");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration");
                alert.setHeaderText("Při registraci se vyskytl problém. Uživatel s tímto jménem již existuje, nebo neexistuje osoba se zadaným rodným číslem.");
                alert.setContentText("Sorry");

                alert.showAndWait();
            }

        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(login, password, RC, savebtn);
        vbox.setPadding(new Insets(10));
        root.getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields() {
        // TODO Auto-generated method stub
        login.clear();
        password.clear();

    }

    public Stage getStage() {
        return primaryStage;
    }
}
