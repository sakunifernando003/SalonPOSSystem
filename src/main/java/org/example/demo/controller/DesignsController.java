package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DesignsController {

    @FXML
    private TableColumn<?, ?> catIDColm;

    @FXML
    private TextField catIDTxt;

    @FXML
    private Button delBtn;

    @FXML
    private TableColumn<?, ?> desIDColm;

    @FXML
    private TextField desIDTxt;

    @FXML
    private TextField desNameTxt;

    @FXML
    private TableView<?> designTbl;

    @FXML
    private TableColumn<?, ?> nameColm;

    @FXML
    private TableColumn<?, ?> priceColm;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TableColumn<?, ?> statusColm;

    @FXML
    private TextField statusTxt;

    @FXML
    private Button updateBtn;

}

