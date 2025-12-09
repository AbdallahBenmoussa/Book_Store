package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TicketController {

    @FXML
    private Label bookTitleLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Label QuantityLable;

    @FXML
    private Label TotalPriceLable;

    @FXML
    private Label UnitPriceLable;

    @FXML
    private Button doneBtn;

    public void setSaleDetails(String bookTitle, double unitPrice, int quantity, double totalPrice) {
        bookTitleLabel.setText(bookTitle);
        UnitPriceLable.setText(String.format("%.2f", unitPrice));
        QuantityLable.setText(String.valueOf(quantity));
        TotalPriceLable.setText(String.format("%.2f", totalPrice));
    }

    @FXML
    void handleClose(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleDone(ActionEvent event) {
        Stage stage = (Stage) doneBtn.getScene().getWindow();
        stage.close();
    }

}
