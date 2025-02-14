package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBO {
    ArrayList<ServiceDTO> getAllServices() throws SQLException, ClassNotFoundException;

    String getNextServiceId() throws SQLException, ClassNotFoundException;

    boolean saveService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;

    boolean deleteService(String serviceId) throws SQLException, ClassNotFoundException;

    boolean updateService(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException;
}
