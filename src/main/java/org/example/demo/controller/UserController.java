package org.example.demo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.demo.bo.BOFactory;
import org.example.demo.bo.custom.UserBO;
import org.example.demo.bo.custom.UserBO;
import org.example.demo.dto.UserDTO;
import org.example.demo.dto.tm.UserTM;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;



public class UserController implements Initializable {
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameColm.setCellValueFactory(new PropertyValueFactory<>("userName"));
        useridColm.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        emailColm.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColm.setCellValueFactory(new PropertyValueFactory<>("role"));
        passColm.setCellValueFactory(new PropertyValueFactory<>("password"));


        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    public void refreshPage() throws Exception {
        loadNextUserId();
        loadTableData();

        saveBTn.setDisable(false);
        updateBtn.setDisable(true);
        deleBtn.setDisable(true);

        userNameTxt.setText("");
        emailTxt.setText("");
        roleTxt.setText("");
        passTxt.setText("");

    }
//   UserBO userModel = new UserModel();

    @FXML
    void onClickTbl(MouseEvent event) {
        UserTM userTM = userTbl.getSelectionModel().getSelectedItem();
        if (userTM != null) {
            userNameTxt.setText(userTM.getUserName());
            userIdLbl.setText(String.valueOf(userTM.getUserID()));
            emailTxt.setText(userTM.getEmail());
            roleTxt.setText(userTM.getRole());
            passTxt.setText(userTM.getPassword());

            saveBTn.setDisable(true);

            updateBtn.setDisable(false);
            deleBtn.setDisable(false);

        }


    }

    private void loadTableData()throws Exception {
        ArrayList<UserDTO> userDTOS = userBO.getAllUsers();

        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

        for (UserDTO userDTO : userDTOS) {
            UserTM userTM = new UserTM(
                 userDTO.getName(),
                    userDTO.getId(),
                    userDTO.getEmail(),
                    userDTO.getRole(),
                    userDTO.getPassword()

            );
           userTMS.add(userTM);
        }
        userTbl.setItems(userTMS);
    }

    private void loadNextUserId() throws SQLException, ClassNotFoundException {
        int nextUserId = Integer.parseInt(userBO.getNextUserId());
        userIdLbl.setText(String.valueOf(nextUserId));
    }

    @FXML
    private AnchorPane UserPage;

    @FXML
    private Button deleBtn;

    @FXML
    private TableColumn<?, ?> emailColm;

    @FXML
    private TextField emailTxt;

    @FXML
    private Label userIdLbl;

    @FXML
    private TableColumn<?, ?> passColm;

    @FXML
    private TextField passTxt;

    @FXML
    private TableColumn<?, ?> roleColm;

    @FXML
    private TextField roleTxt;

    @FXML
    private TableView<UserTM> userTbl;

    @FXML
    private Button saveBTn;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TableColumn<?, ?> useridColm;

    @FXML
    private TableColumn<?, ?> usernameColm;

    @FXML
    void delOnAction(ActionEvent event)throws Exception {
        UserTM selectedUser = (UserTM) userTbl.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer to delete").show();
            return;
        }
        Integer userId = Integer.valueOf(selectedUser.getUserID());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this user?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = userBO.deleteUser(String.valueOf(userId));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User has been deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception{
        String userName = userNameTxt.getText();
        String userId = userIdLbl.getText();
        String email = emailTxt.getText();
        String role = roleTxt.getText();
        String password = passTxt.getText();


        userNameTxt.setStyle(userNameTxt.getStyle() + "-fx-text-fill: red;");
        emailTxt.setStyle(emailTxt.getStyle()+"-fx-text-fill: red;");
        roleTxt.setStyle(roleTxt.getStyle()+"-fx-text-fill: red;");
        passTxt.setStyle(passTxt.getStyle()+"-fx-text-fill: red;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


        boolean isValidName = userName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);



        if (!isValidName) {
            System.out.println(userNameTxt.getStyle());
           userNameTxt.setStyle(userNameTxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidEmail) {
            emailTxt.setStyle(emailTxt.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidName  && isValidEmail ) {
            UserDTO userDTO = new UserDTO(
                    userName,
                    userId,
                    email,
                    role,
                    password

            );

            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save user...!").show();
            }
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws  Exception{
        String userName = userNameTxt.getText();
        String userId = userIdLbl.getText();
        String email = emailTxt.getText();
        String role = roleTxt.getText();
        String password = passTxt.getText();



        userNameTxt.setStyle(userNameTxt.getStyle() + "-fx-text-fill: red;");
        emailTxt.setStyle(emailTxt.getStyle() + "-fx-text-fill: red;");

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


        boolean isValidName = userName.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);

        if (!isValidName) {
            System.out.println(userNameTxt.getStyle());
            userNameTxt.setStyle(userNameTxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }


        if (!isValidEmail) {
            emailTxt.setStyle(emailTxt.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName  && isValidEmail ) {
            UserDTO userDTO = new UserDTO(
                    userName,
                    userId,
                    email,
                    role,
                    password
            );

            boolean isUpdate = userBO.updateUser(userDTO);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "User update...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update user...!").show();
            }
        }


    }

}


