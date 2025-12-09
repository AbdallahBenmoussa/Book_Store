package com.example.tpfinal.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.tpfinal.DAO.BookDAO;
import com.example.tpfinal.DAO.SaleDAO;
import com.example.tpfinal.Model.Book;

public class BrowsBooksController {

    private final BookDAO bookDAO = new BookDAO();
    private final SaleDAO saleDAO = new SaleDAO();

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private Button buyBtn;

    @FXML
    private TableColumn<Book, Long> priceColumn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, Integer> yearColumn;

    @FXML
    private Button backBtn;

    private Parent previousRoot;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("autheur"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadBooks(null);
    }

    @FXML
    void searchBooks(ActionEvent event) {
        loadBooks(searchField.getText());
    }

    @FXML
    void buySelected(ActionEvent event) {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("No selection", "Please select a book to record a sale.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Record sale");
        dialog.setHeaderText("Quantity to sell");
        dialog.setContentText("Enter quantity:");
        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return;
        }

        try {
            int qty = Integer.parseInt(result.get());
            if (qty <= 0) {
                showError("Invalid quantity", "Quantity must be greater than zero.");
                return;
            }
            if (qty > selected.getQuantity()) {
                showError("Not enough stock", "Available quantity: " + selected.getQuantity());
                return;
            }

            double total = selected.getPrice() * qty;
            boolean saleRecorded = saleDAO.recordSale(selected.getBookId(), qty, total);
            boolean stockUpdated = bookDAO.updateBookQuantity(selected.getBookId(), selected.getQuantity() - qty);

            if (saleRecorded && stockUpdated) {
                showInfo("Sale recorded", "Sale saved and stock updated.");
                loadBooks(searchField.getText());
            } else {
                showError("Database error", "Could not record sale. Please try again.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid number", "Please enter a valid quantity.");
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

    public void setBackNavigation(Parent previousRoot) {
        this.previousRoot = previousRoot;
    }

    private void loadBooks(String keyword) {
        List<Book> books = (keyword == null || keyword.isBlank()) ? bookDAO.getAllBooks() : bookDAO.searchBooks(keyword);
        ObservableList<Book> data = FXCollections.observableArrayList(books);
        booksTable.setItems(data);
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
