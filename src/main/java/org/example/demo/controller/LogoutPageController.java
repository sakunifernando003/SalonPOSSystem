package org.example.demo.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogoutPageController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private AnchorPane LogoutPage;

    @FXML
    private Button logagainBtn;

    @FXML
    void logAgainOnAction(ActionEvent event) throws IOException {

        LogoutPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        LogoutPage.getChildren().add(load);

    }

}

