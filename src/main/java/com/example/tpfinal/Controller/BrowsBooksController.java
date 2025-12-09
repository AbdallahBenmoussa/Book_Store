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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

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

    @FXML
    private ComboBox<Book> bookCombo;

    @FXML
    private TextField quantitySoldField;

    @FXML
    private Button recordSaleBtn;

    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("autheur"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadBooks(null);
        
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bookCombo.setValue(newSelection);
                calculateTotal();
            }
        });
        
        bookCombo.valueProperty().addListener((obs, oldVal, newVal) -> calculateTotal());
        quantitySoldField.textProperty().addListener((obs, oldVal, newVal) -> calculateTotal());
        
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchBooks(null);
            }
        });
        
        totalLabel.setText("0.00");
    }

    @FXML
    void searchBooks(ActionEvent event) {
        String keyword = searchField.getText();
        if (keyword == null || keyword.isBlank()) {
            loadBooks(null);
        } else {
            loadBooks(keyword);
        }
    }

    @FXML
    void recordSale(ActionEvent event) {
        Book selected = bookCombo.getValue();
        if (selected == null) {
            showError("No book selected", "Please choose a book.");
            return;
        }
        String qtyText = quantitySoldField.getText();
        if (qtyText == null || qtyText.isBlank()) {
            showError("Missing quantity", "Enter quantity sold.");
            return;
        }
        try {
            int qty = Integer.parseInt(qtyText);
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
                showTicketDialog(selected.getTitle(), selected.getPrice(), qty, total);
                
                quantitySoldField.clear();
                bookCombo.setValue(null);
                totalLabel.setText("0.00");
                loadBooks(searchField.getText());
            } else {
                showError("Database error", "Could not record sale. Please try again.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid number", "Please enter a valid quantity.");
        }
    }
    
    private void calculateTotal() {
        Book selected = bookCombo.getValue();
        String qtyText = quantitySoldField.getText();
        
        if (selected == null || qtyText == null || qtyText.isBlank()) {
            totalLabel.setText("0.00");
            return;
        }
        
        try {
            int qty = Integer.parseInt(qtyText);
            if (qty > 0) {
                double total = selected.getPrice() * qty;
                totalLabel.setText(String.format("%.2f", total));
            } else {
                totalLabel.setText("0.00");
            }
        } catch (NumberFormatException ex) {
            totalLabel.setText("0.00");
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
    }

    private void loadBooks(String keyword) {
        List<Book> books = (keyword == null || keyword.isBlank()) ? bookDAO.getAllBooks() : bookDAO.searchBooks(keyword);
        ObservableList<Book> data = FXCollections.observableArrayList(books);
        booksTable.setItems(data);
        bookCombo.setItems(data);
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
    
    private void showTicketDialog(String bookTitle, double unitPrice, int quantity, double totalPrice) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tpfinal/ticket.fxml"));
            Parent root = loader.load();
            
            TicketController ticketController = loader.getController();
            ticketController.setSaleDetails(bookTitle, unitPrice, quantity, totalPrice);
            
            Stage ticketStage = new Stage();
            ticketStage.setTitle("Sale Receipt");
            ticketStage.initModality(Modality.APPLICATION_MODAL);
            ticketStage.initStyle(StageStyle.UTILITY);
            ticketStage.setScene(new Scene(root));
            ticketStage.setResizable(false);
            ticketStage.showAndWait();
        } catch (IOException e) {
            showInfo("Sale recorded", String.format("Sale saved successfully!\nBook: %s\nQuantity: %d\nTotal: %.2f", 
                    bookTitle, quantity, totalPrice));
            e.printStackTrace();
        }
    }
}
