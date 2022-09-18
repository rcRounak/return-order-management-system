package com.example.authorizationService.controller;

import com.example.authorizationService.exceptions.InvalidCredentialsException;
import com.example.authorizationService.model.UserRequest;
import com.example.authorizationService.util.JwtUtil;
import com.example.authorizationService.exceptions.InvalidCredentialsException;
import com.example.authorizationService.model.User;
import com.example.authorizationService.model.UserRequest;
import com.example.authorizationService.repository.UserRepository;
import com.example.authorizationService.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin
public class AuthorizationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userService;

    @Value("${userDetails.badCredentialsMessage}")
    private String BAD_CREDENTIALS_MESSAGE;

    @Value("${userDetails.disabledAccountMessage}")
    private String DISABLED_ACCOUNT_MESSAGE;

    @Value("${userDetails.lockedAccountMessage}")
    private String LOCKED_ACCOUNT_MESSAGE;

    @PostMapping("/login")
    @Operation(summary="Performs login", description = "Generates JWT token")
    public @ApiResponse(description="Returns JWT token")ResponseEntity<String> login(@Parameter(description = "Takes user details - Username and Password")@RequestBody @Valid UserRequest userRequest) {
        log.info("START - login()");

        if (userService.loadUserByUsername(userRequest.getUsername()) != null) {

            try {
                userRequest.getPassword().equalsIgnoreCase(userService.loadUserByUsername(userRequest.getUsername()).getPassword());
                log.info("Valid User detected - logged in");
            } catch (BadCredentialsException e) {
                throw new InvalidCredentialsException(BAD_CREDENTIALS_MESSAGE);
            } catch (DisabledException e) {
                throw new InvalidCredentialsException(DISABLED_ACCOUNT_MESSAGE);
            } catch (LockedException e) {
                throw new InvalidCredentialsException(LOCKED_ACCOUNT_MESSAGE);
            }
    }

        String token = jwtUtil.generateToken(userRequest.getUsername());
        log.info("END - login");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/validate")
    @Operation(summary="Performs JWT validation", description = "Validates JWT token")
    public @ApiResponse(description="Returns either True or False")ResponseEntity<Boolean> validate(@Parameter(description = "Takes authorization token") @RequestHeader(name = "Authorization") String token) {
        log.info("START - validation");

        // throws custom exception and response if token is invalid
        jwtUtil.isTokenExpiredOrInvalidFormat(token);

        if(userService.loadUserByUsername(jwtUtil.getUsernameFromToken(token))!=null) {
            log.info("END - validation");
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
}
