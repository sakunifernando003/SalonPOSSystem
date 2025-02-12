package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.dao.SuperDAO;
import org.example.demo.entity.Customer;
import org.example.demo.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmplyeeDAO extends CrudDAO<Employee> {


    ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean delete(String empId) throws SQLException, ClassNotFoundException;

    boolean save(Employee employee) throws SQLException, ClassNotFoundException;

    boolean update(Employee employee) throws SQLException, ClassNotFoundException;
}
