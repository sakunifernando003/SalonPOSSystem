package org.example.demo.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Service {
    private int serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private int serviceDuration;

}
