package com.teatching_app.model.dto;

import com.teatching_app.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Setter
public class UserDTO {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String roleName;
    private String password;



    public UserDTO(UserEntity user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.roleName = user.getRole().getRoleName();
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getRoleName() {
        return roleName;
    }

    public String encodePassword() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
