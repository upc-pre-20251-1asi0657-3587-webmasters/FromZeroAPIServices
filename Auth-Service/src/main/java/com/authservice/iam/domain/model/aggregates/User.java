package com.authservice.iam.domain.model.aggregates;

import com.authservice.iam.domain.model.commands.SignUpDeveloperCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private UUID userId;

    @Setter
    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(max = 50)
    private String userEmail;

    @Setter
    @NotBlank
    @Column(nullable = false)
    @Size(max = 120)
    private String userPassword;

    @Setter
    @NotBlank
    @Column(nullable = false)
    private String userRole;

    @PrePersist
    public void generateId() {
        if (this.userId == null) {
            this.userId = UUID.randomUUID();
        }
    }

    public User() {  }

    public User(SignUpDeveloperCommand signUpCommand, String userPassword) {
        this.userEmail = signUpCommand.userEmail();
        this.userPassword = userPassword;
        this.userRole = signUpCommand.userRole();
    }

    public User(SignUpDeveloperCommand signUpCommand, String userPassword, String userRole) {
        this.userEmail = signUpCommand.userEmail();
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
