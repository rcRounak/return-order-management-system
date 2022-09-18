package com.example.authorizationService.repository;

import com.example.authorizationService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
