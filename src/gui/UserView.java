/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wooow
 */
package gui;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spolupracejavisti.Databaze;
import spolupracejavisti.Databaze;
import spolupracejavisti.Uzivatel;

/**
 *
 * @author wooow
 */
public class UserView {

    private BorderPane borderPane;
    private MenuBar menuBar;
    private VBox vbox;
    private Databaze databaze;
    private Uzivatel uzivatel;

    public UserView(Databaze databaze, Uzivatel uzivatel) {
        this.databaze = databaze;
        this.uzivatel = uzivatel;
        init();
    }

    public void init() {
        Stage primaryStage = new Stage();

        menuBar = new MenuBar();
        vbox = new VBox();
        borderPane = new BorderPane();

        // FIRMY
        Menu menu = new Menu("Firmy");
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_FIRMU)) {
            MenuItem menuItem = new MenuItem("Zobrazit informace o firme");
            menuItem.setOnAction(e -> {
                FirmaView firmaView = new FirmaView(databaze, uzivatel);
            });
            menu.getItems().add(menuItem);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_FIRMU)) {
            MenuItem menuItem2 = new MenuItem("Pridat novou firmu");
            menuItem2.setOnAction(e -> {
                FirmaNew firmaNew = new FirmaNew(databaze, uzivatel);
                Stage stage = firmaNew.getStage();
                stage.show();
            });
            menu.getItems().add(menuItem2);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.SMAZAT_FIRMU)) {
            MenuItem menuItem3 = new MenuItem("Smazat firmu");
            menuItem3.setOnAction(e -> {
                FirmaDelete firmaDelete = new FirmaDelete(databaze, uzivatel);
                Stage stage = firmaDelete.getStage();
                stage.show();
            });
            menu.getItems().add(menuItem3);
        }

        // SPOLUPRÁCE
        Menu menu2 = new Menu("Spoluprace");
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_SPOLUPRACI)) {
            MenuItem menuItemSpoluprace = new MenuItem("Zobrazit informace o spolupraci");
            menuItemSpoluprace.setOnAction(e -> {
                SpolupraceView spolupraceView = new SpolupraceView(databaze, uzivatel);
            });
            menu2.getItems().add(menuItemSpoluprace);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_SPOLUPRACI)) {
            MenuItem menuItemSpoluprace2 = new MenuItem("Pridat novou spolupraci");
            menuItemSpoluprace2.setOnAction(e -> {
                SpolupraceNew spolupraceNew = new SpolupraceNew(databaze, uzivatel);
                Stage stage = spolupraceNew.getStage();
                stage.show();
            });
            menu2.getItems().add(menuItemSpoluprace2);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.SMAZAT_SPOLUPRACI)) {
            MenuItem menuItemSpoluprace3 = new MenuItem("Smazat spolupraci");
            
            menu2.getItems().add(menuItemSpoluprace3);
        }

        // TYPY SPOLUPRÁCE
        Menu menu3 = new Menu("Typ spoluprace");
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_TYP_SPOLUPRACE)) {
            MenuItem menuItemTypSpoluprace = new MenuItem("Zobrazit typy spoluprací");
            menuItemTypSpoluprace.setOnAction(e -> {
                TypSpolupraceView typSpolupraceView = new TypSpolupraceView(databaze, uzivatel);
            });
            menu3.getItems().add(menuItemTypSpoluprace);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_TYP_SPOLUPRACE)) {
            MenuItem menuItemTypSpoluprace2 = new MenuItem("Pridat nový typ spoluprace");
            menuItemTypSpoluprace2.setOnAction(e -> {
                TypSpolupraceNew typSpolupraceNew = new TypSpolupraceNew(databaze, uzivatel);
                Stage stage = typSpolupraceNew.getStage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();
            });
            menu3.getItems().add(menuItemTypSpoluprace2);
        }

        // OSOBY
        Menu menu4 = new Menu("Osoby");
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.ZOBRAZIT_OSOBU)) {
            MenuItem menuOsoby = new MenuItem("Zobrazit seznam osob");
            menuOsoby.setOnAction(e -> {
                OsobaView osobaView = new OsobaView(databaze, uzivatel);
            });
            menu4.getItems().add(menuOsoby);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.PRIDAT_OSOBU)) {
            MenuItem menuOsoby2 = new MenuItem("Pridat novou osobu");
            menuOsoby2.setOnAction(e -> {
                OsobaNew osobaNew = new OsobaNew(databaze, uzivatel);
                Stage stage = osobaNew.getStage();
                stage.show();
            });
            menu4.getItems().add(menuOsoby2);
        }
        if (uzivatel.maOpravneni(Uzivatel.Opravneni.SMAZAT_OSOBU)) {
            MenuItem menuOsoby3 = new MenuItem("Smazat osobu");
            menuOsoby3.setOnAction(e -> {
                OsobaDelete osobaDelete = new OsobaDelete(databaze, uzivatel);
                Stage stage = osobaDelete.getStage();
                stage.show();
            });
            menu4.getItems().add(menuOsoby3);
        }

        ImageView img = new ImageView(new Image("/zdroje/08.jpg"));
        img.fitWidthProperty().bind(borderPane.widthProperty());
        menuBar.getMenus().addAll(menu, menu2, menu3, menu4);
        borderPane.setCenter(img);
        vbox.getChildren().addAll(menuBar, borderPane);

        Scene scene = new Scene(vbox, 500, 400);

        primaryStage.setTitle("Spoluprace javisti");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
