package org.example.demo.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class User {
    private String id;
    private String name;
    private String email;
    private String role;
    private String password;

}
