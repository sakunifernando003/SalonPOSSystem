package org.example.demo.bo.custom;

import org.example.demo.dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeBO {
    ArrayList<EmployeeDTO> getAllEmployees();

    String getNextEmployeeId();

    boolean deleteEmployee(int empId);

    boolean saveEmployee(EmployeeDTO employeeDTO);

    boolean updateEmployee(EmployeeDTO employeeDTO);

}
