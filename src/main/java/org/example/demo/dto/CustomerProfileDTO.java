package org.example.demo.dto;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class CustomerProfileDTO {
    private String profileId;
    private String customerId;
    private String membershipStatus;
    private int appointmentCount;
    private int loyalPoints;
    private String specialNeeds;


}
