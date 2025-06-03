package com.authservice.iam.domain.model.entities;

import com.authservice.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private Roles name;

    public Role() {  }

    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }
}
