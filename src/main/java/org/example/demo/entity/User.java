package org.example.demo.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User {
    private String name;
    private int id;
    private String email;
    private String role;
    private String password;

    public User(String userName, String userId, String email, String role, String password) {
    }
}
