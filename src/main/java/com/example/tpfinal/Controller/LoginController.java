package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController {

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
    void login(ActionEvent event) {

    }

    @FXML
    void  exitApp(ActionEvent event){
        System.exit(0);
    }

}
