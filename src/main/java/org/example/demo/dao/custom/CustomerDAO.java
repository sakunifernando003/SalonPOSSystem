package org.example.demo.dao.custom;

import org.example.demo.dao.SuperDAO;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends SuperDAO {

    boolean save(Customer customer) throws SQLException, ClassNotFoundException;

    boolean delete(String custId) throws SQLException, ClassNotFoundException;

    boolean update(Customer customer) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;
}
