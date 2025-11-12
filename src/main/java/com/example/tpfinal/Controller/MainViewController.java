package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainViewController {

    @FXML
    private Button manageBookBtn;

    @FXML
    private Button browseBtn;

    @FXML
    private VBox mainContainer;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label quickAccessLabel;

    @FXML
    private Label shortcutAddBook;

    @FXML
    private Label shortcutAddBookDesc;

    @FXML
    private Label shortcutBrowse;

    @FXML
    private Label shortcutBrowseDesc;

    @FXML
    private Label shortcutOrder;

    @FXML
    private Label shortcutOrderDesc;

    @FXML
    private Label subtitleLabel;

    @FXML
    private VBox welcomeCard;

    @FXML
    private ImageView welcomeIcon;

    @FXML
    private Label welcomeLabel;

    @FXML
    void browseBooks(ActionEvent event) {

    }

    @FXML
    void viewReports(ActionEvent event) {

    }

    @FXML
    public void ajouterLivre() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/add-book.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter une demande");
            stage.initStyle(StageStyle.UNDECORATED);

            GaussianBlur blur = new GaussianBlur(10);
            mainContainer.setEffect(blur);
            stage.setOnHidden(e -> mainContainer.setEffect(null));

            stage.show();
        } catch (IOException ex) {
            System.err.println("Failed to load FXML: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
