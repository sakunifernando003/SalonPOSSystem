package org.example.demo.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.demo.bo.custom.CustomerBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.CustomerDAO;
import org.example.demo.dto.CustomerDTO;
import org.example.demo.entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;



public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(),customer.getName(),customer.getPhone(),customer.getEmail()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getId(),customer.getName(),customer.getPhone(),customer.getEmail()));
    }


    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        ArrayList<Customer> customers1 = customerDAO.getAll();
        for (Customer customer : customers1) {
            customers.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getPhone(),customer.getEmail()));
        }
        return customers;
    }

    @Override
    public String getNextCustomerId() throws SQLException, ClassNotFoundException {

        return customerDAO.getNextId();

    }

    @Override
    public boolean deleteCustomer(String custId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(custId);
    }
}
