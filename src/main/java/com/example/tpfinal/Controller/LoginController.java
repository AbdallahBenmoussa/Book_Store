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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/main-view.fxml"));
        root = loader.load();

        MainViewController MainViewController = loader.getController();

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

}
