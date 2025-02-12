package org.example.demo.bo.custom.Impl;

import org.example.demo.bo.custom.EmployeeBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.Impl.EmployeeDAOImpl;
import org.example.demo.dto.EmployeeDTO;
import org.example.demo.entity.Customer;
import org.example.demo.entity.Employee;
import org.example.demo.dao.custom.EmplyeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmplyeeDAO emplyeeDAO = (EmplyeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employees = emplyeeDAO.getAll();
        for (Employee employee : employees) {
            employeeDTOS.add(new EmployeeDTO(employee.getEmpId(),employee.getName(),employee.getPhone(),employee.getEmail(),employee.getSpecialization(),employee.getAddress()));
        }
        return employeeDTOS;
    }

    @Override
    public String getNextEmployeeId() throws SQLException, ClassNotFoundException {
        return emplyeeDAO.getNextId();
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return emplyeeDAO.delete(empId);
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return emplyeeDAO.save(new Employee(employee.getEmpId(),employee.getName(),employee.getPhone(),employee.getEmail(),employee.getSpecialization(),employee.getAddress()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return emplyeeDAO.update(new Employee(employee.getEmpId(),employee.getName(),employee.getPhone(),employee.getEmail(),employee.getSpecialization(),employee.getAddress()));
    }
}
