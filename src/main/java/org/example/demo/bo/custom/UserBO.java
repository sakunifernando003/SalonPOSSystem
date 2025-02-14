package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    String getNextUserId() throws SQLException, ClassNotFoundException;

    boolean deleteUser(String s) throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;


    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
