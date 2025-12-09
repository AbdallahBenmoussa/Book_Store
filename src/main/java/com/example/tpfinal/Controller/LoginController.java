package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.tpfinal.DAO.UserDAO;
import com.example.tpfinal.Model.User;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final UserDAO userDAO = new UserDAO();

    @FXML
    private Button exit_btn;

    @FXML
    private Button login_btn;

    @FXML
    private Label login_title;

    @FXML
    private Pane main_Panel;

    @FXML
    private PasswordField password_filed;

    @FXML
    private TextField userName_field;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = userName_field.getText();
        String password = password_filed.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            showError("Missing credentials", "Please enter both username and password.");
            return;
        }

        User user = userDAO.authenticate(username, password);
        if (user == null) {
            showError("Invalid credentials", "Username or password is incorrect.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/main-view.fxml"));
        root = loader.load();

        MainViewController mainViewController = loader.getController();
        mainViewController.setConnectedUser(user);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Book Store - Main Window");
        stage.show();
    }

    @FXML
    void  exitApp(ActionEvent event){
        System.exit(0);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
