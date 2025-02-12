package org.example.demo.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Employee {
    private String empId;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private String address;
}
