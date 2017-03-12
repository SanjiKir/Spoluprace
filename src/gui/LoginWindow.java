/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spolupracejavisti.Databaze;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.control.PasswordField;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class LoginWindow extends Application {

    VBox vBox;
    Databaze databaze;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Database inicialicion
        databaze = new Databaze();

        Text text = new Text("Login");
        TextField loginTextField = new TextField();

        Text textPassword = new Text("Password ");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Your password");

        HBox hBoxLogin = new HBox(30);
        vBox = new VBox(5);
        hBoxLogin.getChildren().addAll(text, loginTextField);
        hBoxLogin.setAlignment(Pos.CENTER);

        HBox hBoxPassword = new HBox(5);
        hBoxPassword.getChildren().addAll(textPassword, passwordTextField);
        hBoxPassword.setAlignment(Pos.CENTER);

        Button login = new Button("Sign In");
        login.setOnAction(e -> {
            if (loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong login or password");
                alert.setHeaderText("Check your login or password");
                alert.setContentText("check it!");

                alert.showAndWait();
            } else if (databaze.signInAttempt(loginTextField.getText(), passwordTextField.getText())) {
                 // if (databaseConnect.isAdmin(loginTextField.getText())) {
                 //   UserView main = new UserView();
               // } else {
               Uzivatel uzivatel = databaze.getUzivatel(loginTextField.getText());
               UserView main = new UserView(databaze, uzivatel);
               // }
                primaryStage.close();
              
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong login or password");
                alert.setHeaderText("Check your login or password");
                alert.setContentText("check it!");

                alert.showAndWait();
            }
        });

        Button signUp = new Button("Sign Up");
        signUp.setOnAction(e -> {
            SignUpWindow signUpWindow = new SignUpWindow(databaze);
            Stage stage = signUpWindow.getStage();
            stage.show();
        });

        HBox buttonHBox = new HBox(5);
        buttonHBox.getChildren().addAll(login, signUp);
        buttonHBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBoxLogin, hBoxPassword, buttonHBox);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        Scene scene = new Scene(vBox, 500, 300);

        primaryStage.setTitle("LogIn window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

