package com.teatching_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;


    @Column(name = "password")
    String password;


    @Column(name = "name")
    String name;


    @Column(name = "surname")
    String surname;


    @Column(name = "email")
    String email;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "role")
    RoleEntity role;

    @OneToMany(mappedBy = "user")
    Set<CourseEntity> userCourses ;

    public UserEntity() {
    }
}
