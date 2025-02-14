package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField addreTxt;

    @FXML
    private Button deltBtn;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField suppEmailTxt;

    @FXML
    private TextField suppIDTxt;

    @FXML
    private TextField suppNameTxt;

    @FXML
    private TableView<?> supplierTbl;

    @FXML
    private Button updateBtn;

}

