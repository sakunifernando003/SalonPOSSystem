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
import org.example.demo.bo.custom.AppointmentBO;
import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.dto.AppointmentDTO;
import org.example.demo.dto.tm.AppointmentTM;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {
//
//    private final AppointmentModel appointmentModel = new AppointmentModel();
//    private final CustomerModel customerModel = new CustomerModel();
//    private final ServiceModel serviceModel = new ServiceModel();
AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.APPOINTMENT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    @FXML
    private ComboBox<String> CustomerID;

    @FXML
    private ComboBox<Integer> ServiceID;

    @FXML
    private ComboBox<String> StatusBox;

    @FXML
    private Label appoLbl;

    @FXML
    private TableView<AppointmentTM> appoTbl;

    @FXML
    private TableColumn<AppointmentTM, String> appointColm;

    @FXML
    private TableColumn<AppointmentTM, String> customerColm;

    @FXML
    private TableColumn<AppointmentTM, String> dateCom;

    @FXML
    private TableColumn<AppointmentTM, String> serviceColm;

    @FXML
    private TableColumn<AppointmentTM, String> statusColm;


    @FXML
    private DatePicker dateTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deltBtn;

    @FXML
    void custIdOnAction(ActionEvent event) {

    }

    @FXML
    void serviceIdOnAction(ActionEvent event) {

    }

    @FXML
    void statusOnAction(ActionEvent event) {

    }

    @FXML
    void userIdOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        if (!validateFields()) return;

        String appointmentId = appoLbl.getText();
        String customerId = CustomerID.getValue();
        Integer serviceId = ServiceID.getValue();
        String status = StatusBox.getValue();
        Date appointmentDate = java.sql.Date.valueOf(dateTxt.getValue());

        AppointmentDTO appointmentDTO = new AppointmentDTO(
                appointmentId,
                customerId,
                serviceId,
                (java.sql.Date) appointmentDate,
                status
        );

        boolean isSaved = appointmentBO.saveAppointment(appointmentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Appointment saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the appointment!").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws Exception {
        if (!validateFields()) return;

        String appointmentId = appoLbl.getText();
        String customerId = CustomerID.getValue();
        Integer serviceId = ServiceID.getValue();
        String status = StatusBox.getValue();
        Date appointmentDate = java.sql.Date.valueOf(dateTxt.getValue());

        AppointmentDTO appointmentDTO = new AppointmentDTO(
                appointmentId,
                customerId,
                serviceId,
                (java.sql.Date) appointmentDate,
                status
        );

        boolean isUpdated = appointmentBO.updateAppointment(appointmentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Appointment updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update the appointment!").show();
        }
    }

    @FXML
    void delOnAction(ActionEvent event) throws Exception {
        AppointmentTM selectedAppointment = appoTbl.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an appointment to delete.").show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            boolean isDeleted = appointmentBO.deleteAppointment(selectedAppointment.getAppointmentId());
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Appointment deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the appointment!").show();
            }
        }
    }

    @FXML
    void onClickTbl(MouseEvent event) {
        AppointmentTM selectedAppointment = appoTbl.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            appoLbl.setText(selectedAppointment.getAppointmentId());
            CustomerID.setValue(selectedAppointment.getCustomerId());
            ServiceID.setValue(Integer.valueOf(selectedAppointment.getServiceId()));
            StatusBox.setValue(selectedAppointment.getStatus());
            dateTxt.setValue(((java.sql.Date) selectedAppointment.getDate()).toLocalDate());

            saveBtn.setDisable(true);
            updateBtn.setDisable(false);
            deltBtn.setDisable(false);
        }
    }

    private void loadComboBoxData() throws SQLException {
//
//        ObservableList<String> customers = FXCollections.observableArrayList(customerBO.getAllCustomerIds());
//        CustomerID.setItems(customers);
//
//
//        ArrayList<Integer> serviceIds = serviceModel.getAllServiceIds();
//        ObservableList<Integer> services = FXCollections.observableArrayList(serviceIds);
//        ServiceID.setItems(services);
//
//

        StatusBox.setItems(FXCollections.observableArrayList("Pending", "Completed", "Cancelled"));

        StatusBox.setValue("Pending");
    }

    private boolean validateFields() {
        if (CustomerID.getValue() == null || ServiceID.getValue() == null || StatusBox.getValue() == null || dateTxt.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointColm.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        customerColm.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        serviceColm.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        dateCom.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColm.setCellValueFactory(new PropertyValueFactory<>("status"));


        try {
            loadComboBoxData();
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data.").show();
        }
    }

    private void refreshPage() throws Exception {
        loadTableData();
        loadNextAppointment();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
        deltBtn.setDisable(true);

        CustomerID.setValue(null);
        ServiceID.setValue(null);
        StatusBox.setValue(null);
        dateTxt.setValue(null);
    }

    private void loadTableData() throws SQLException {
        ArrayList<AppointmentDTO> appointments = appointmentBO.getAllAppointments();
        ObservableList<AppointmentTM> appointmentList = FXCollections.observableArrayList();

        for (AppointmentDTO dto : appointments) {
            appointmentList.add(new AppointmentTM(
                    dto.getAppointmentId(),
                    dto.getCustomerId(),
                    dto.getServiceId(),
                    dto.getDate(),
                    dto.getStatus()
            ));
        }
        appoTbl.setItems(appointmentList);
    }

    private void loadNextAppointment() throws SQLException, ClassNotFoundException {
        String nextId = appointmentBO.getNextAppointmentId();
        appoLbl.setText(nextId);
    }

    public void dateOnAction(ActionEvent actionEvent) {
        System.out.println("Date action triggered!");
    }
}

