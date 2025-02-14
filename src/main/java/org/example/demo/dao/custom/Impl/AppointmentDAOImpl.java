package org.example.demo.dao.custom.Impl;

import org.example.demo.dao.SQLUtil;
import org.example.demo.dao.custom.AppointmentDAO;
import org.example.demo.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public boolean delete(String appointmentId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM appointment WHERE app_id = ?",appointmentId);
    }


    @Override
    public boolean save(Appointment appointment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO appointment (app_id, cust_id, service_id, appointment_date, status) VALUES (?, ?, ?, ?, ?)",
                appointment.getAppointmentId(),
                appointment.getCustomerId(),
                appointment.getServiceId(),
                appointment.getAppointmentDate(),
                appointment.getStatus()

                );
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE appointment SET cust_id = ?, service_id = ?, appointment_date = ?, status = ?, user_id = ? WHERE app_id = ?",
                appointment.getCustomerId(),
                appointment.getServiceId(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),


                appointment.getAppointmentId()

                );
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       ResultSet resultSet= SQLUtil.execute("SELECT app_id FROM appointment ORDER BY app_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
