package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {
    ArrayList<User> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean delete(Integer userId) throws SQLException, ClassNotFoundException;

    boolean save(User user) throws SQLException, ClassNotFoundException;


}
