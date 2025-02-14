package org.example.demo.bo.custom;

import org.example.demo.bo.SuperBO;
import org.example.demo.dto.AppointmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBO extends SuperBO {
    boolean saveAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;

    boolean deleteAppointment(String appointmentId) throws SQLException, ClassNotFoundException;

    ArrayList<AppointmentDTO> getAllAppointments();

    String getNextAppointmentId() throws SQLException, ClassNotFoundException;
}
