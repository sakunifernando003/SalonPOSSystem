package org.example.demo.dao.custom;

import org.example.demo.dao.SuperDAO;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmplyeeDAO extends SuperDAO{
    public ArrayList<Customer> getCustomers() throws SQLException;



}
