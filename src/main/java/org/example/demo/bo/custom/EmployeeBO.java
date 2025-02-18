package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dao.SuperDAO;
import org.example.demo.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    String getNextEmployeeId() throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

}
