package com.example.tpfinal.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BrowsBooksController {

    @FXML
    private TableColumn<?, ?> authorColumn;

    @FXML
    private TableView<?> booksTable;

    @FXML
    private Button buyBtn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> priceColumn1;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> yearColumn;

}
