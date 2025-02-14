package org.example.demo.dao.custom.Impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.ServiceDAO;
import org.example.demo.dto.ServiceDTO;
import org.example.demo.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public  class ServiceDAOImpl implements ServiceDAO {
    @Override
    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM nail_care_service ");
        ArrayList<Service> serviceList = new ArrayList<>();

        while (resultSet.next()){
            serviceList.add(new Service(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDouble(4),resultSet.getInt(5) ));
        }
        return serviceList;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('0', LPAD(IFNULL(MAX(SUBSTRING(service_id, 2)) + 1, 1), 3, '0')) AS next_service_id FROM nail_care_service;");
       if (resultSet.next()){
           return resultSet.getString(1);
       }
        return null;
    }

    @Override
    public boolean save(ServiceDTO serviceDTO) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean save(Service service) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO  nail_care_service (service_id, name, description, price, duration) VALUES (?, ?, ?, ?,?)",
                service.getServiceId(),
                service.getServiceName(),
                service.getServiceDescription(),

                service.getServicePrice(),
                service.getServiceDuration()

                );
    }

    @Override
    public boolean delete(String serviceId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM nail_care_service WHERE service_id = ?",serviceId);
    }

    @Override
    public boolean update(Service service) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE nail_care_service SET name = ?, description = ?, price = ?, duration = ? WHERE service_id = ?",


                service.getServiceName(),
                service.getServiceDescription(),

                service.getServicePrice(),
                service.getServiceDuration(),
                service.getServiceId()
                );
    }
}
