package org.example.demo.dto.tm;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ServiceTM extends CustomerTM {
    private int serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private int serviceDuration;
}
