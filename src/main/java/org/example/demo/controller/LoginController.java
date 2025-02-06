package org.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane content;

    @FXML
    void onActionLinkUser(ActionEvent event) throws IOException {

        content.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/User.fxml"));
        content.getChildren().add(load);
    }

    @FXML
    private AnchorPane loginPage;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField passwordtxt;

    @FXML
    private TextField usernametxt;

    @FXML
    void clickOnAction(ActionEvent event) throws IOException {
        String username = usernametxt.getText();
        String password = passwordtxt.getText();

        if (username.equals("admin") && password.equals("123")) {



            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashBoard.fxml"));
            content.getChildren().setAll(load);
        } else {
            new Alert(Alert.AlertType.ERROR, "Try again").show();
        }
    }

}










