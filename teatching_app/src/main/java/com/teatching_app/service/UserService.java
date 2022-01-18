package com.teatching_app.service;

import com.teatching_app.exceptionHandler.exception.ResourceNotExistsException;
import com.teatching_app.model.dto.UserDTO;
import com.teatching_app.model.entity.RoleEntity;
import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.RoleRepository;
import com.teatching_app.repository.UserRepository;
import com.teatching_app.validator.UserDataValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDataValidator userDataValidator;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserDataValidator userDataValidator, UserRepository userRepository, RoleRepository roleRepository) {
        this.userDataValidator = userDataValidator;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO registerNewUser(UserDTO newUser) {
        userDataValidator.validData(newUser);

        RoleEntity role = roleRepository.findRoleByName(newUser.getRoleName());
        UserEntity userToSave = new UserEntity(newUser,role);
        UserEntity user = userRepository.save(userToSave);
        return new UserDTO(user);
    }

    public UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String login = auth.getName();
        UserEntity currentUser = userRepository.findUserByUsername(login)
                .orElseThrow(() -> new ResourceNotExistsException("Username not exist"));
        return currentUser;
    }
}
