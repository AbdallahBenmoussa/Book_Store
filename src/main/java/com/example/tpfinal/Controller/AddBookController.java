package com.example.tpfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.tpfinal.DAO.BookDAO;
import com.example.tpfinal.Model.Book;

public class AddBookController {

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private Button fermer_button;

    @FXML
    private Pane main_Panel;

    @FXML
    private Button Ajt_Livre_Btn;

    @FXML
    private Button back_btn;

    @FXML
    private TextField title_field;

    @FXML
    private TextField author_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextField quantity_field;

    @FXML
    void fermer_button_onAction(ActionEvent event) {
        Stage stage = (Stage) fermer_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleConfirmerButton(ActionEvent event) {
        String title = title_field.getText();
        String author = author_field.getText();
        String priceText = price_field.getText();
        String quantityText = quantity_field.getText();

        if (title == null || title.isBlank() || author == null || author.isBlank()
                || priceText == null || priceText.isBlank() || quantityText == null || quantityText.isBlank()) {
            showError("Missing data", "Please fill all fields before saving.");
            return;
        }

        try {
            long price = Long.parseLong(priceText);
            int quantity = Integer.parseInt(quantityText);

            Book book = new Book(0, quantity, author, title, price);
            boolean inserted = bookDAO.addBook(book);
            if (inserted) {
                showInfo("Success", "Book saved successfully.");
                clearForm();
            } else {
                showError("Database error", "Failed to save the book. Check connection or data.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid numbers", "Please enter valid numeric values for price and quantity.");
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tpfinal/main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Book Store - Main Window");
        stage.show();
    }

    public void setBackNavigation(Parent unused) {
    }

    private void clearForm() {
        title_field.clear();
        author_field.clear();
        price_field.clear();
        quantity_field.clear();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
