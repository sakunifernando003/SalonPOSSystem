package org.example.demo.bo.custom.Impl;

import org.example.demo.bo.custom.EmployeeBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.Impl.EmployeeDAOImpl;
import org.example.demo.dto.EmployeeDTO;
import org.example.demo.entity.Employee;

import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAOImpl employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() {
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        ArrayList<Employee> employees1 = employeeDAO.getAll();
        for (Employee employee : employees1) {
            employees.add(new EmployeeDTO(employee.getEmpId(),));
        }

    }

    @Override
    public String getNextEmployeeId() {
        return "";
    }

    @Override
    public boolean deleteEmployee(int empId) {
        return false;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) {
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return false;
    }

}
