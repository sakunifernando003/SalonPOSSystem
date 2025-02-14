package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Button deltBtn;

    @FXML
    private TableView<?> inventryTbl;

    @FXML
    private TextField invtIdTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField proNameTxt;

    @FXML
    private TextField qtyTxt;

    @FXML
    private TextField rptIdTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField supIdTxt;

    @FXML
    private Button updateBtn;

}

