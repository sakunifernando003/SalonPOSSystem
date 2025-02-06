package org.example.demo.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class EmployeeDTO {
    private int empId;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private String address;
}
