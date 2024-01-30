package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.AuthService;
import com.example.schooljournal.services.exceptions.EmailAlreadyRegisteredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthEndpoint extends GenericEndpoint{
    private AuthService service;

    @PostMapping(path = "/login", consumes = {"application/json"})
    public ResponseEntity<String> login(@RequestBody User u, HttpServletRequest req, HttpServletResponse res) {
        try {
            service.loginUser(u, req, res);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Logged in", HttpStatus.OK);
    }

    @PostMapping(path = "/register", consumes = {"application/json"})
    public ResponseEntity<User> registerUser(@RequestBody User u) {
        try {
            return ResponseEntity.ok(service.registerUser(u));
        } catch (EmailAlreadyRegisteredException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "/currentUser")
    public ResponseEntity<User> currentUser() {
        return ResponseEntity.ok(getCurrentUser());
    }
}
