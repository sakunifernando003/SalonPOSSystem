package org.example.demo.bo.custom.Impl;

import org.example.demo.bo.custom.ServiceBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.ServiceDAO;
import org.example.demo.dto.ServiceDTO;
import org.example.demo.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    @Override
    public ArrayList<ServiceDTO> getAllServices() throws SQLException, ClassNotFoundException {
        ArrayList<ServiceDTO> serviceDTOS = new ArrayList<>();
        ArrayList<Service> services = serviceDAO.getAll();
        for (Service service : services) {
            serviceDTOS.add(new ServiceDTO(service.getServiceId(),service.getServiceName(),service.getServiceDescription(),service.getServicePrice(),service.getServiceDuration()));
        }
        return serviceDTOS;
    }

    @Override
    public String getNextServiceId() throws SQLException, ClassNotFoundException {
        return serviceDAO.getNextId();
    }

    @Override
    public boolean saveService(ServiceDTO service) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new ServiceDTO(service.getServiceId(),service.getServiceName(),service.getServiceDescription(),service.getServicePrice(),service.getServiceDuration()));
    }

    @Override
    public boolean deleteService(String serviceId) throws SQLException, ClassNotFoundException {
        return serviceDAO.delete(serviceId);
    }

    @Override
    public boolean updateService(ServiceDTO service) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(service.getServiceId(),service.getServiceName(),service.getServiceDescription(),service.getServicePrice(),service.getServiceDuration()));
    }
}
