package org.example.demo.dao.custom.Impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.EmplyeeDAO;
import org.example.demo.entity.Customer;
import org.example.demo.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmplyeeDAO {


    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> employees = new ArrayList<>();

        while (resultSet.next()){
            employees.add(new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
        }


        return employees;
    }

    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (emp_id, name, phone, email,specialization,address) VALUES (?, ?, ?, ?,?,?)",
                employee.getEmpId(),
                employee.getName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getSpecialization(),
                employee.getAddress()
                );
    }

    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?, phone = ?, email = ? , specialization =?, address= ? WHERE emp_id = ?",

                employee.getName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getSpecialization(),
                employee.getAddress(),
                employee.getEmpId()


        );
    }

    public boolean delete(String empId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE emp_id = ?", empId);

    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('E', LPAD(IFNULL(MAX(SUBSTRING(emp_id, 2)) + 1, 1), 3, '0')) AS next_emp_id FROM employee;");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
