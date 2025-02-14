package org.example.demo.bo.custom.Impl;

import org.example.demo.bo.custom.AppointmentBO;
import org.example.demo.dao.DAOFactory;
import org.example.demo.dao.custom.AppointmentDAO;
import org.example.demo.dao.custom.CustomerDAO;
import org.example.demo.dto.AppointmentDTO;
import org.example.demo.dto.CustomerDTO;
import org.example.demo.entity.Appointment;
import org.example.demo.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;


public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);

    @Override
    public boolean saveAppointment(AppointmentDTO appointment) throws SQLException, ClassNotFoundException {

        return appointmentDAO.save(new Appointment(appointment.getAppointmentId(), appointment.getCustomerId(), appointment.getServiceId(), appointment.getAppointmentDate(), appointment.getStatus()));
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointment) throws SQLException, ClassNotFoundException {
        return appointmentDAO.update(new Appointment(appointment.getAppointmentId(), appointment.getCustomerId(), appointment.getServiceId(), appointment.getAppointmentDate(), appointment.getStatus()));
    }

    @Override
    public boolean deleteAppointment(String appointmentId) throws SQLException, ClassNotFoundException {
        return appointmentDAO.delete(appointmentId);
    }

    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() {
        ArrayList<AppointmentDTO> appointments = new ArrayList<>();
        ArrayList<Appointment> appointments1 = AppointmentDAO.getAll();
        for (Appointment appointment : appointments1) {
            appointments.add(new AppointmentDTO(appointment.getAppointmentId(), appointment.getCustomerId(), appointment.getServiceId(), appointment.getAppointmentDate(), appointment.getStatus()));

        }
        return appointments;
    }

    @Override
    public String getNextAppointmentId() throws SQLException, ClassNotFoundException {
        return appointmentDAO.getNextId();
    }
}
