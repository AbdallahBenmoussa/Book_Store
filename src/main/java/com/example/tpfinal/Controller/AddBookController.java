package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddBookController {


    @FXML
    private Button fermer_button;

    @FXML
    private Pane main_Panel;

    @FXML
    private Button Ajt_Livre_Btn;


    @FXML
    void fermer_button_onAction(ActionEvent event) {
        Stage stage = (Stage) fermer_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleConfirmerButton(ActionEvent event) {

    }

}
