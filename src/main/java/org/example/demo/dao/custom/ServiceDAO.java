package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.dto.ServiceDTO;
import org.example.demo.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends CrudDAO<Service> {

    ArrayList<Service> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean save(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean save(Service service) throws SQLException, ClassNotFoundException;

    boolean delete(String serviceId) throws SQLException, ClassNotFoundException;

    boolean update(Service service) throws SQLException, ClassNotFoundException;
}
