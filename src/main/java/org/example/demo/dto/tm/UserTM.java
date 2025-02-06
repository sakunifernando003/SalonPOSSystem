package org.example.demo.dto.tm;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserTM {
    private String UserName;
    private Integer UserID;
    private String email;
    private String role;
    private String password;


}
