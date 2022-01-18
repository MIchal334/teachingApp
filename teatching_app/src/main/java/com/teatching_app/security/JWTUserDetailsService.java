package com.teatching_app.security;

import com.teatching_app.model.entity.UserEntity;
import com.teatching_app.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JWTUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));
        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
