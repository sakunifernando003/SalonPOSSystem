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
import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.bo.custom.Impl.CustomerBOImpl;
import org.example.demo.dto.CustomerDTO;
import org.example.demo.dto.tm.CustomerTM;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<?, ?> custIdColmn;


    @FXML
    private TableColumn<?, ?> custNameColm;

    @FXML
    private TextField custNameTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> emailColm;

    @FXML
    private TextField emailTxt;

    @FXML
    private TableColumn<?, ?> phonColmn;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Label lblCustId;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custIdColmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        custNameColm.setCellValueFactory(new PropertyValueFactory<>("name"));
        phonColmn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColm.setCellValueFactory(new PropertyValueFactory<>("email"));


        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }

    }
    public void refreshPage() throws Exception {
        loadNextCustomerId();
        loadTableData();

        saveBtn.setDisable(false);
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);


        custNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");

    }
    /*CustomerModel customerModel = new CustomerModel();*/

    @FXML
    void onClickTbl(MouseEvent event) {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            lblCustId.setText(customerTM.getId());
            custNameTxt.setText(customerTM.getName());
            phoneTxt.setText(customerTM.getPhone());
            emailTxt.setText(customerTM.getEmail());

            saveBtn.setDisable(true);

            updateBtn.setDisable(false);
            deleteBtn.setDisable(false);
        }
    }

    @FXML
    void delOnAction(ActionEvent event) throws Exception {
        CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer to delete").show();
            return;
        }
        String custId = selectedCustomer.getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = customerBO.deleteCustomer(custId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer has been deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }


    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        String custId = lblCustId.getText();
        String email = emailTxt.getText();
        String phone = phoneTxt.getText();
        String custName = custNameTxt.getText();

        custNameTxt.setStyle(custNameTxt.getStyle() + "-fx-text-fill: red;");
        phoneTxt.setStyle(phoneTxt.getStyle()+"-fx-text-fill: red;");
        emailTxt.setStyle(emailTxt.getStyle()+"-fx-text-fill: red;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = custName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);



        if (!isValidName) {
            System.out.println(custNameTxt.getStyle());
            custNameTxt.setStyle(custNameTxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidEmail) {
            emailTxt.setStyle(emailTxt.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            phoneTxt.setStyle(phoneTxt.getStyle() + ";-fx-border-color: red;");
        }
        if (isValidName  && isValidEmail && isValidPhone) {
            CustomerDTO customerDTO = new CustomerDTO(
                    custId,
                    custName,
                    phone,
                    email

            );

            boolean isSaved = customerBO.saveCustomer(customerDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }


    }


    @FXML
    void updateOnAction(ActionEvent event) throws Exception {
        String custId = lblCustId.getText();
        String email = emailTxt.getText();
        String phone = phoneTxt.getText();
        String custName = custNameTxt.getText();


        custNameTxt.setStyle("");
        emailTxt.setStyle("");
        phoneTxt.setStyle("");


        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^\\d{10}$";


        boolean isValidName = custName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            custNameTxt.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name format!").show();
            return;
        }
        if (!isValidEmail) {
            emailTxt.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid email format!").show();
            return;
        }
        if (!isValidPhone) {
            phoneTxt.setStyle("-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number format!").show();
            return;
        }


        CustomerDTO customerDTO = new CustomerDTO(
                custId,
                custName,
                phone,
                email
        );




        try{
            boolean isUpdate = customerBO.updateCustomer(customerDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = customerBO.getAllCustomers();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getId(),
                    customerDTO.getName(),
                    customerDTO.getPhone(),
                    customerDTO.getEmail()

            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);
    }

    public void loadNextCustomerId() throws SQLException, ClassNotFoundException {
        String nextCustomerId = customerBO.getNextCustomerId();
        lblCustId.setText(nextCustomerId);

    }


}

























