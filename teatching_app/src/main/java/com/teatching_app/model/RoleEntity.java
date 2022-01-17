package com.teatching_app.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Short id;

    @Column(name = "role_name")
    String roleName;

    @OneToMany(mappedBy = "role")
    Set<UserEntity> users;

    public RoleEntity() {
    }
}
