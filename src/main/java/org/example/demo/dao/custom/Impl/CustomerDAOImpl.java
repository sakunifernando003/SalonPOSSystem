package org.example.demo.dao.custom.Impl;

import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.CustomerDAO;
import org.example.demo.entity.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (customer_id, name, phone, email) VALUES (?, ?, ?, ?)",
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail()
                );


    }

    @Override
    public boolean delete(String custId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?", custId);
    }

    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, phone = ?, email = ? WHERE customer_id = ?",

                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getId()
                );


    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return customers;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
      ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('C', LPAD(IFNULL(MAX(SUBSTRING(customer_id, 2)) + 1, 1), 3, '0')) AS next_customer_id FROM customer;");
      if (resultSet.next()) {
          return resultSet.getString(1);
      }
        return null;
    }


}
