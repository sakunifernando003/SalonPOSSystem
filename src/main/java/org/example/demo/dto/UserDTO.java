package org.example.demo.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class UserDTO {
    private String name;
    private int id;
    private String email;
    private String role;
    private String password;

    public UserDTO(String userName, String userId, String email, String role, String password) {
    }
}
