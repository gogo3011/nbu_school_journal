package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

}
