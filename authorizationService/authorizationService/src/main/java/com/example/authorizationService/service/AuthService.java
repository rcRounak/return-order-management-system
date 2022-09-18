package com.example.authorizationService.service;

import com.example.authorizationService.exceptions.InvalidCredentialsException;
import com.example.authorizationService.model.User;
import com.example.authorizationService.repository.UserRepository;
import com.example.authorizationService.exceptions.InvalidCredentialsException;
import com.example.authorizationService.model.User;
import com.example.authorizationService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    @Value("${userDetails.errorMessage}")
    private String USER_DOES_NOT_EXIST_MESSAGE;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new InvalidCredentialsException(USER_DOES_NOT_EXIST_MESSAGE);
        } else {
            log.info("Username: {} is valid", username);
            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    new ArrayList<>());
        }
    }

}
