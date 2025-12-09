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

import java.io.IOException;
import java.util.List;

import com.example.tpfinal.DAO.BookDAO;
import com.example.tpfinal.Model.Book;

public class ManageBooksController {

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Book> booksTable;

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
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("autheur"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> populateForm(newSel));
        loadBooks(null);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tpfinal/main-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Book Store - Main Window");
        stage.show();
    }

    @FXML
    void searchBooks(ActionEvent event) {
        loadBooks(searchField.getText());
    }

    @FXML
    void addBook(ActionEvent event) {
        if (!validateForm()) return;
        Book book = new Book(0, Integer.parseInt(quantityField.getText()), authorField.getText(), titleField.getText(), Long.parseLong(priceField.getText()));
        boolean ok = bookDAO.addBook(book);
        if (ok) {
            showInfo("Added", "Book added successfully.");
            clearForm();
            searchField.clear();
            loadBooks(null);
            booksTable.refresh();
        } else {
            showError("DB error", "Could not add book. Check database connection.");
        }
    }

    @FXML
    void editBook(ActionEvent event) {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("No selection", "Select a book to edit.");
            return;
        }
        if (!validateForm()) return;

        selected.setTitle(titleField.getText());
        selected.setAutheur(authorField.getText());
        selected.setPrice(Long.parseLong(priceField.getText()));
        selected.setQuantity(Integer.parseInt(quantityField.getText()));

        boolean ok = bookDAO.updateBook(selected);
        if (ok) {
            showInfo("Updated", "Book updated successfully.");
            loadBooks(searchField.getText());
        } else {
            showError("DB error", "Could not update book.");
        }
    }

    @FXML
    void deleteBook(ActionEvent event) {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("No selection", "Select a book to delete.");
            return;
        }
        boolean ok = bookDAO.deleteBook(selected.getBookId());
        if (ok) {
            showInfo("Deleted", "Book deleted.");
            clearForm();
            loadBooks(searchField.getText());
        } else {
            showError("DB error", "Could not delete book.");
        }
    }

    @FXML
    void refreshList(ActionEvent event) {
        searchField.clear();
        loadBooks(null);
    }

    private void populateForm(Book book) {
        if (book == null) {
            clearForm();
            return;
        }
        titleField.setText(book.getTitle());
        authorField.setText(book.getAutheur());
        priceField.setText(String.valueOf(book.getPrice()));
        quantityField.setText(String.valueOf(book.getQuantity()));
    }

    private void loadBooks(String keyword) {
        try {
            List<Book> books = (keyword == null || keyword.isBlank()) ? bookDAO.getAllBooks() : bookDAO.searchBooks(keyword);
            ObservableList<Book> data = FXCollections.observableArrayList(books);
            booksTable.setItems(data);
            booksTable.refresh();
        } catch (Exception e) {
            showError("Error", "Failed to load books: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validateForm() {
        String title = titleField.getText();
        String author = authorField.getText();
        String priceText = priceField.getText();
        String qtyText = quantityField.getText();
        if (title == null || title.isBlank() || author == null || author.isBlank()
                || priceText == null || priceText.isBlank() || qtyText == null || qtyText.isBlank()) {
            showError("Missing data", "Fill all fields.");
            return false;
        }
        try {
            long price = Long.parseLong(priceText);
            int qty = Integer.parseInt(qtyText);
            if (price < 0 || qty < 0) {
                showError("Invalid values", "Price and quantity must be non-negative.");
                return false;
            }
        } catch (NumberFormatException ex) {
            showError("Invalid numbers", "Enter valid numeric price and quantity.");
            return false;
        }
        return true;
    }

    private void clearForm() {
        titleField.clear();
        authorField.clear();
        priceField.clear();
        quantityField.clear();
        booksTable.getSelectionModel().clearSelection();
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
