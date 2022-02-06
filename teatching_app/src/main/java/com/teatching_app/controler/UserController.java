package com.teatching_app.controler;

import com.teatching_app.model.dto.LoginDTO;
import com.teatching_app.model.dto.UserDTO;
import com.teatching_app.model.dto.jwtResponse;
import com.teatching_app.security.JWTUserDetailsService;
import com.teatching_app.security.JwtUtil;
import com.teatching_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.ws.rs.NotAuthorizedException;

@RestController
@EnableSwagger2
public class UserController {

    private final UserService userService;
    private final JWTUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public UserController(UserService userService, JWTUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/profile")
    ResponseEntity<UserDTO> getCurrentUser() {
        var result = userService.getCurrentUser();
        return ResponseEntity.ok(new UserDTO(result));
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserDTO newUser) {
        var result = userService.registerNewUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PostMapping("/auth")
    ResponseEntity<?> getToken(@RequestBody LoginDTO loginData) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new NotAuthorizedException("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginData.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new jwtResponse(jwt));

    }

}
