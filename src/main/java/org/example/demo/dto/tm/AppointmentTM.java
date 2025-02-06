package org.example.demo.dto.tm;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AppointmentTM {
    private String appointmentId;
    private String customerId;
    private int userId;
    private String serviceId;
    private Date appointmentDate;
    private String status;

    public AppointmentTM(String appointmentId, String customerId, String serviceId, Object date, String status) {
    }

    public Object getDate() {
        return appointmentDate;
    }
}
