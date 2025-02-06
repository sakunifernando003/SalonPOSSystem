package org.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Appointment {
    private String appointmentId;
    private String customerId;
    private String serviceId;
    private Date appointmentDate;
    private String status;

    public Appointment(String appointmentId, String customerId, String serviceId, Date appointmentDate, String status) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public Appointment(String appointmentId, String customerId, Integer serviceId, java.sql.Date appointmentDate, String status) {

    }

    public Object getDate() {
        return appointmentDate;
    }
}
