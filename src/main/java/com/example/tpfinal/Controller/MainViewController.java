package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

import com.example.tpfinal.Model.User;

public class MainViewController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private User connectedUser;

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
    private MenuItem menuAddBook;

    @FXML
    private MenuItem menuBrowse;

    @FXML
    void browseBooks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/brows-books.fxml"));
        root = loader.load();

        BrowsBooksController browsBooksController = loader.getController();
        browsBooksController.setBackNavigation(mainContainer);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Brows Books");
        stage.show();
    }

    @FXML
    void openAddBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/add-book.fxml"));
        root = loader.load();

        AddBookController addBookController = loader.getController();
        addBookController.setBackNavigation(mainContainer);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Add Book");
        stage.show();
    }

    @FXML
    void viewReports(ActionEvent event) {

    }

    @FXML
    public void ajouterLivre() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/brows-books.fxml"));
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

    public void setConnectedUser(User user) {
        this.connectedUser = user;
        if (welcomeLabel != null && user != null && user.getUsername() != null) {
            welcomeLabel.setText("Welcome back, " + user.getUsername() + "!");
        }
    }

}
