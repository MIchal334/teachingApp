package com.teatching_app.model.entity;

import com.teatching_app.model.dto.UserDTO;
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

    @Column(name = "last_lesson_number")
    Integer lastLesson;

    @Column(name = "current_level_number")
    Integer currentLevel;

    @ManyToOne
    @JoinColumn(name = "role")
    RoleEntity role;

    @OneToMany(mappedBy = "user")
    Set<CourseEntity> userCourses ;

    public UserEntity() {
    }

    public UserEntity(UserDTO newUser, RoleEntity role) {
        this.username = newUser.getUsername();
        this.password = newUser.encodePassword();
        this.name = newUser.getName();
        this.surname = newUser.getSurname();
        this.email = newUser.getEmail();
        this.role = role;
        this.isDeleted = false;
        this.lastLesson = 0;
        this.currentLevel = 0;
    }
}
