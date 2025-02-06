package org.example.demo.dto.tm;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TransactionTM {
    private String payNum;
    private String id;
    private String amount;
    private String quantity;
    private String date;
}
