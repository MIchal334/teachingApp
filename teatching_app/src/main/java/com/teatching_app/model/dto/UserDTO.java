package com.teatching_app.model.dto;

import com.teatching_app.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    String username;
    String name;
    String surname;
    String email;
    String roleName;
    String password;

    public UserDTO() {
    }

    public UserDTO(UserEntity user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.roleName = user.getRole().getRoleName();
    }
}
