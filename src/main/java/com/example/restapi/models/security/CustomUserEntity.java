package com.example.restapi.models.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login")
})
@Data
@NoArgsConstructor
public class CustomUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String role;

    public CustomUserEntity(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
