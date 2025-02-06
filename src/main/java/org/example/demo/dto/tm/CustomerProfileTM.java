package org.example.demo.dto.tm;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class CustomerProfileTM {
    private String profileId;
    private String customerId;
    private String membershipStatus;
    private int appointmentCount;
    private int loyalPoints;
    private String specialNeeds;


}
