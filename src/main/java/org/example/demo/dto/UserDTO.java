package org.example.demo.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String role;
    private String password;

}
