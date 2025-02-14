package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.EmployeeBO;
import org.example.demo.bo.custom.ServiceBO;
import org.example.demo.dto.ServiceDTO;
import org.example.demo.dto.tm.CustomerTM;
import org.example.demo.dto.tm.ServiceTM;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {
    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SERVICE);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    serviceIdColm.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
    serviceNameColm.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
    descColm.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
    priceColm.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
    durationColm.setCellValueFactory(new PropertyValueFactory<>("serviceDuration"));

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextServiceId();
        loadTableData();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);


        serviceNameTxt.setText("");
        descTxt.setText("");
        priceTxt.setText("");
        durationTxt.setText("");

    }
//    ServiceModel serviceModel = new ServiceModel();

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = serviceBO.getAllServices();

        ObservableList<CustomerTM> serviceTMS = FXCollections.observableArrayList();

        for (ServiceDTO serviceDTO : serviceDTOS) {
            ServiceTM serviceTM = new ServiceTM(
                    serviceDTO.getServiceId(),
                    serviceDTO.getServiceName(),
                    serviceDTO.getServiceDescription(),
                    serviceDTO.getServicePrice(),
                    serviceDTO.getServiceDuration()

            );
            serviceTMS.add(serviceTM);
        }
        servicetbl.setItems(serviceTMS);
    }

    private void loadNextServiceId() throws SQLException, ClassNotFoundException {
       String nextServiceId = serviceBO.getNextServiceId();
        serviceIdLbl.setText(String.valueOf(nextServiceId));
    }

    @FXML
    private Button deleteBtn;

    @FXML
    private Label serviceIdLbl;

    @FXML
    private TableColumn<?, ?> descColm;

    @FXML
    private TextArea descTxt;

    @FXML
    private TableColumn<?, ?> durationColm;

    @FXML
    private TextField durationTxt;

    @FXML
    private TableColumn<?, ?> priceColm;

    @FXML
    private TextField priceTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TableColumn<?, ?> serviceIdColm;

    @FXML
    private TableColumn<?, ?> serviceNameColm;

    @FXML
    private TextField serviceNameTxt;

    @FXML
    private TableView<CustomerTM> servicetbl;

    @FXML
    private Button updateBtn;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ServiceTM selectedService = (ServiceTM) servicetbl.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a service to delete").show();
            return;
        }
        String serviceId = String.valueOf(selectedService.getServiceId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this service?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = serviceBO.deleteService(serviceId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Services been deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }

    @FXML
    void onClickTbl(MouseEvent event) throws SQLException {
        ServiceTM serviceTM = (ServiceTM) servicetbl.getSelectionModel().getSelectedItem();
        if (serviceTM != null) {
            serviceIdLbl.setText(String.valueOf(serviceTM.getServiceId()));
            serviceNameTxt.setText(serviceTM.getServiceName());
            descTxt.setText(serviceTM.getServiceDescription());
            priceTxt.setText(String.valueOf(serviceTM.getServicePrice()));
            durationTxt.setText(String.valueOf(serviceTM.getServiceDuration()));

            saveBtn.setDisable(true);

            updateBtn.setDisable(false);
            deleteBtn.setDisable(false);

        }


    }
    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        int serviceId = Integer.parseInt(serviceIdLbl.getText());
        String serviceName = serviceNameTxt.getText();
        String description = descTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int duration = Integer.parseInt(durationTxt.getText());

        serviceNameTxt.setStyle(serviceNameTxt.getStyle()+"-fx-text-fill: purple;");
        descTxt.setStyle(descTxt.getStyle()+"-fx-text-fill: purple;");
        priceTxt.setStyle(priceTxt.getStyle()+"-fx-text-fill: purple;");
        durationTxt.setStyle(priceTxt.getStyle()+"-fx-text-fill: purple;");


        String namePattern = "^[A-Za-z ]+$";


        boolean isValidName = serviceName.matches(namePattern);


        if (!isValidName) {
            System.out.println(serviceNameTxt.getStyle());
            serviceNameTxt.setStyle(serviceNameTxt.getStyle() + ";-fx-border-color: purple;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (isValidName ) {
            ServiceDTO serviceDTO = new ServiceDTO(
                    serviceId,
                    serviceName,
                    description,
                    price,
                    duration

            );

            boolean isSaved = serviceBO.saveService(serviceDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Service saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save service...!").show();
            }
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        int serviceId = Integer.parseInt(serviceIdLbl.getText());
        String serviceName = serviceNameTxt.getText();
        String description = descTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int duration = Integer.parseInt(durationTxt.getText());

        // Reset styles
        serviceNameTxt.setStyle("");
        descTxt.setStyle("");
        priceTxt.setStyle("");
        durationTxt.setStyle("");


        // Validation patterns
        String namePattern = "^[A-Za-z ]+$";


        // Validate inputs
        boolean isValidName = serviceName.matches(namePattern);


        if (!isValidName) {
            serviceNameTxt.setStyle("-fx-border-color: purple;");
            new Alert(Alert.AlertType.ERROR, "Invalid name format!").show();
            return;
        }


        ServiceDTO serviceDTO = new ServiceDTO(
                serviceId,
                serviceName,
                description,
                price,
                duration
        );

        try{
            boolean isUpdate = serviceBO.updateService(serviceDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "service updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update service!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}


