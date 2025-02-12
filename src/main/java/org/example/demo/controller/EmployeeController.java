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
import org.example.demo.dto.EmployeeDTO;
import org.example.demo.dto.tm.EmployeeTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Employee);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColm.setCellValueFactory(new PropertyValueFactory<>("name"));
        IdColm.setCellValueFactory(new PropertyValueFactory<>("empId"));
        phoneColm.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColm.setCellValueFactory(new PropertyValueFactory<>("email"));
        specializeColm.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        addressColm.setCellValueFactory(new PropertyValueFactory<>("address"));

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextEmployeeId();
        loadTableData();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
        delBtn.setDisable(true);


        empNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        addressTxt.setText("");
        specialTxt.setText("");

    }
    /*EmployeeModel employeeModel = new EmployeeModel();
*/
    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = employeeBO.getAllEmployees();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getEmpId(),
                    employeeDTO.getName(),
                    employeeDTO.getPhone(),
                    employeeDTO.getEmail(),
                    employeeDTO.getSpecialization(),
                    employeeDTO.getAddress()


            );
            employeeTMS.add(employeeTM);
        }
        empTbl.setItems(employeeTMS);
    }

    private void loadNextEmployeeId() throws SQLException, ClassNotFoundException {
        String nextEmployeeId = employeeBO.getNextEmployeeId();
        empIdLbl.setText(nextEmployeeId);
    }

    @FXML
    private TableColumn<?, ?> IdColm;

    @FXML
    private TableColumn<?, ?> addressColm;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button delBtn;

    @FXML
    private TableColumn<?, ?> emailColm;

    @FXML
    private TextField emailTxt;

    @FXML
    private Label empIdLbl;

    @FXML
    private TextField empNameTxt;

    @FXML
    private TableView<EmployeeTM> empTbl;

    @FXML
    private TableColumn<?, ?> nameColm;

    @FXML
    private TableColumn<?, ?> phoneColm;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField specialTxt;

    @FXML
    private TableColumn<?, ?> specializeColm;

    @FXML
    private Button updateBtn;

    @FXML
    void delOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        EmployeeTM selectedEmployee = empTbl.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a employee to delete").show();
            return;
        }
        String empId = selectedEmployee.getEmpId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this employee?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = employeeBO.deleteEmployee(empId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee has been deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }

    }

    @FXML
    void onClickTbl(MouseEvent event) {
        EmployeeTM employeeTM = empTbl.getSelectionModel().getSelectedItem();
        if (employeeTM!= null) {
            empIdLbl.setText(String.valueOf(employeeTM.getEmpId()));
            empNameTxt.setText(employeeTM.getName());
            phoneTxt.setText(employeeTM.getPhone());
            emailTxt.setText(employeeTM.getEmail());
            specialTxt.setText(employeeTM.getSpecialization());
            addressTxt.setText(employeeTM.getAddress());


            saveBtn.setDisable(true);

            updateBtn.setDisable(false);
            delBtn.setDisable(false);
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String empId =empIdLbl.getText();
        String name = empNameTxt.getText();
        String phone = phoneTxt.getText();
        String email = emailTxt.getText();
        String specialization = specialTxt.getText();
        String address = addressTxt.getText();


        empNameTxt.setStyle(empNameTxt.getStyle() + "-fx-text-fill: purple;");
        phoneTxt.setStyle(phoneTxt.getStyle()+"-fx-text-fill: purple;");
        emailTxt.setStyle(emailTxt.getStyle()+"-fx-text-fill: purple;");
        specialTxt.setStyle(specialTxt+"-fx-text-fill: purple;");
        addressTxt.setStyle(addressTxt.getStyle()+"-fx-text-fill: purple;");


        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);



        if (!isValidName) {
            System.out.println(empNameTxt.getStyle());
            empNameTxt.setStyle(empNameTxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");

        }
        if (!isValidEmail) {
            emailTxt.setStyle(emailTxt.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            phoneTxt.setStyle(phoneTxt.getStyle() + ";-fx-border-color: red;");
        }
        if (isValidName  && isValidEmail && isValidPhone) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    empId,
                    name,
                    phone,
                    email,
                    specialization,
                    address


            );

            boolean isSaved = employeeBO.saveEmployee(employeeDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save employee...!").show();
            }
        }


    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String empId = empIdLbl.getText();
        String name = empNameTxt.getText();
        String phone = phoneTxt.getText();
        String email = emailTxt.getText();
        String specialization = specialTxt.getText();
        String address = addressTxt.getText();



        empNameTxt.setStyle("");
        emailTxt.setStyle("");
        phoneTxt.setStyle("");
        specialTxt.setStyle("");
        addressTxt.setStyle("");


        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^\\d{10}$";


        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            empNameTxt.setStyle("-fx-border-color: purple;");
            new Alert(Alert.AlertType.ERROR, "Invalid name format!").show();
            return;
        }
        if (!isValidEmail) {
            emailTxt.setStyle("-fx-border-color: purple;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
            return;
        }
        if (!isValidPhone) {
            phoneTxt.setStyle("-fx-border-color: purple;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number format!").show();
            return;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO(
                empId,
                name,
                phone,
                email,
                specialization,
                address


        );



        try{
            boolean isUpdate = employeeBO.updateEmployee(employeeDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update employee!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}


