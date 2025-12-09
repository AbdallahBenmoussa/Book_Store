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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
    private MenuItem menuManageBooks;

    @FXML
    public void initialize() {
        javafx.application.Platform.runLater(() -> {
            setupKeyboardShortcuts();
        });
    }

    private void setupKeyboardShortcuts() {
        Scene currentScene = mainContainer.getScene();
        if (currentScene == null) {
            javafx.application.Platform.runLater(() -> setupKeyboardShortcuts());
            return;
        }
        
        KeyCombination ctrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        currentScene.getAccelerators().put(ctrlS, () -> {
            try {
                openManageSalesWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        KeyCombination ctrlB = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
        currentScene.getAccelerators().put(ctrlB, () -> {
            try {
                openManageBooksWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        KeyCombination ctrlD = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        currentScene.getAccelerators().put(ctrlD, () -> {
            try {
                disconnectToLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    private void openManageSalesWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/brows-books.fxml"));
        root = loader.load();

        BrowsBooksController browsBooksController = loader.getController();
        browsBooksController.setBackNavigation(mainContainer);

        stage = (Stage) mainContainer.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Manage Sales");
        stage.show();
    }
    
    private void openManageBooksWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/manage-books.fxml"));
        root = loader.load();

        stage = (Stage) mainContainer.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Manage Books");
        stage.show();
    }
    
    private void disconnectToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/login-view.fxml"));
        root = loader.load();

        stage = (Stage) mainContainer.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Book Store !");
        stage.show();
    }

    @FXML
    void browseBooks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/brows-books.fxml"));
        root = loader.load();

        BrowsBooksController browsBooksController = loader.getController();
        browsBooksController.setBackNavigation(mainContainer);

        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = (Stage) mainContainer.getScene().getWindow();
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Manage Sales");
        stage.show();
    }

    @FXML
    void openAddBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/add-book.fxml"));
        root = loader.load();

        AddBookController addBookController = loader.getController();
        addBookController.setBackNavigation(mainContainer);

        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = (Stage) mainContainer.getScene().getWindow();
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Add Book");
        stage.show();
    }

    @FXML
    void openManageBooks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/manage-books.fxml"));
        root = loader.load();

        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = (Stage) mainContainer.getScene().getWindow();
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Manage Books");
        stage.show();
    }

    @FXML
    void disconnect(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/login-view.fxml"));
        root = loader.load();

        if (event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else {
            stage = (Stage) mainContainer.getScene().getWindow();
        }
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Book Store !");
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
