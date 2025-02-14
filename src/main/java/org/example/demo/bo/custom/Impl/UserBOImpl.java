package org.example.demo.bo.custom.Impl;

import org.example.demo.bo.custom.UserBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.UserDAO;
import org.example.demo.dto.UserDTO;
import org.example.demo.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
       ArrayList<UserDTO> userDTOS = new ArrayList<>();
       ArrayList<User> users = userDAO.getAll();
       for (User user : users) {
           userDTOS.add(new UserDTO(user.getName(),user.getId(),user.getEmail(),user.getRole(),user.getPassword()));
       }
        return userDTOS;
    }

    @Override
    public String getNextUserId() throws SQLException, ClassNotFoundException {
        return userDAO.getNextId();
    }

    @Override
    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return userDAO.delete(Integer.valueOf(userId));
    }

    @Override
    public boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(user.getName(),user.getId(),user.getEmail(),user.getRole(),user.getPassword()));
    }

    @Override
    public boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(user.getName(),user.getId(),user.getEmail(),user.getRole(),user.getPassword()));
    }


}
