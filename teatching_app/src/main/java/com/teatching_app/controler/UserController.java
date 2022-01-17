package com.teatching_app.controler;

import com.teatching_app.model.dto.UserDTO;
import com.teatching_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserDTO newUser){
        var result  = userService.registerNewUser(newUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
