package com.teatching_app.validator;

import com.teatching_app.exceptionHandler.exception.ResourceAlreadyExistsException;
import com.teatching_app.exceptionHandler.exception.WrongFormatException;
import com.teatching_app.model.dto.UserDTO;
import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.RoleRepository;
import com.teatching_app.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Component
public class UserDataValidator {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDataValidator(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void validData(UserDTO newUser) {
        validUsername(newUser.getUsername());
        validEmail(newUser.getEmail());
        validEmailFormat(newUser.getEmail());
        //roleValidator(newUser.getRoleName());
    }

    private void validUsername(String username) {
        Optional<UserEntity> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            throw new ResourceAlreadyExistsException("User with this username exist");
        }
    }

    private void validEmail(String email) {
        Optional<UserEntity> user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            throw new ResourceAlreadyExistsException("User with this email exist");
        }
    }

    private void validEmailFormat(String email) {
        OptionalInt indexAt = email.chars()
                .filter(c -> c == 64)
                .findFirst();

        if (indexAt.isEmpty()) {
            throw new WrongFormatException("The email has wrong format");
        }
    }


//    private void roleValidator(String roleName) {
//        List<String> roleList= roleRepository.findRoleNameWithoutAdmin();
//        if (!roleList.contains(roleName)) {
//            throw new IllegalArgumentException("This role is not available");
//        }
//    }

}