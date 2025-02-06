package org.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private AnchorPane MainButtnLayout;

    @FXML
    private AnchorPane MainLayout;

    @FXML
    private Button appoinBtn;

    @FXML
    private Button dashCustBtn;

    @FXML
    private Button dashCustProBtn;

    @FXML
    private Button dashUserBtn;

    @FXML
    private Button designBtn;

    @FXML
    private Button empBtn;

    @FXML
    private Button inventBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button packBtn;

    @FXML
    private AnchorPane parentPage;

    @FXML
    private Button performBtn;

    @FXML
    private AnchorPane DashBoardLayer;

    @FXML
    private Button reportBtn;

    @FXML
    private Button servCatBtn;

    @FXML
    private Button serviceBtn;

    @FXML
    private Button supplierBtn;

    @FXML
    private Button transBtn;

    @FXML
    void navigateToTransaction(ActionEvent event) {
    navigateTo("/view/transactionView.fxml");
    }

    @FXML
    void navigateToUserPage(ActionEvent event) {
       navigateTo("/view/User.fxml");
    }
    @FXML
    void navigateToCustomerPage(ActionEvent event) {
        navigateTo("/view/customerView.fxml");
    }

    @FXML
    void navigateToEmployee(ActionEvent event) {
    navigateTo("/view/employeeView.fxml");
    }

    @FXML
    void navigateToCustomerProfile(ActionEvent event) {
    navigateTo("/view/customerprofileView.fxml");
    }

    @FXML
    void navigateToService(ActionEvent event) {
    navigateTo("/view/serviceView.fxml");
    }


    @FXML
    void navigateToAppointment(ActionEvent event) {
    navigateTo("/view/appointmentView.fxml");
    }

    @FXML
    void navigateToSupplier(ActionEvent event) {
    navigateTo("/view/supplierView.fxml");
    }

    @FXML
    void navigateToPerformance(ActionEvent event) {
        navigateTo("/view/performanceView.fxml");
    }

    @FXML
    void navigateToInventory(ActionEvent event) {
    navigateTo("/view/inventoryView.fxml");
    }

    @FXML
    void navigateToReport(ActionEvent event) {
    navigateTo("/view/reportView.fxml");
    }

    @FXML
    void navigateToPackage(ActionEvent event) {
    navigateTo("/view/packagesView.fxml");
    }

    @FXML
    void navigateToDesign(ActionEvent event) {
    navigateTo("/view/designsView.fxml");
    }

    @FXML
    void navigateToDashboard(ActionEvent event)throws IOException {
        parentPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/dashBoard.fxml"));
        parentPage.getChildren().add(load);
    }

    @FXML
    void navigateToLogOutPage(ActionEvent event) throws IOException {
        parentPage.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/logoutPage.fxml"));
        parentPage.getChildren().add(load);

    }

    @FXML
    void navigateToserviceCatalog(ActionEvent event) {
    navigateTo("/view/serviceCatalogView.fxml");
    }





        private void navigateTo(String fxmlPath) {
        try {
            MainLayout.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            MainLayout.getChildren().add(load);

            load.prefWidthProperty().bind(MainLayout.widthProperty());
            load.prefHeightProperty().bind(MainLayout.heightProperty());


        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }



}




