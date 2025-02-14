package org.example.demo.dao.custom;

import org.example.demo.dao.CrudDAO;
import org.example.demo.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends CrudDAO<Appointment> {


    static ArrayList<Appointment> getAll() {
        return null;
    }

    boolean delete(String appointmentId) throws SQLException, ClassNotFoundException;

    boolean save(Appointment appointment) throws SQLException, ClassNotFoundException;

    boolean update(Appointment appointment) throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;
}
