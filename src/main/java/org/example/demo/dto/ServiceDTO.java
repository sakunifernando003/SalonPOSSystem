package org.example.demo.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ServiceDTO {
    private int serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private int serviceDuration;

}
