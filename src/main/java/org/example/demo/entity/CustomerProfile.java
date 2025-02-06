package org.example.demo.entity;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class CustomerProfile {
    private String profileId;
    private String customerId;
    private String membershipStatus;
    private int appointmentCount;
    private int loyalPoints;
    private String specialNeeds;


}
