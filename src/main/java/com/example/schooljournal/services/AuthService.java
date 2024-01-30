package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.services.exceptions.EmailAlreadyRegisteredException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user by checking if the email is already registered. If not, encodes the password and saves the user.
     *
     * @param u The User object to be registered.
     * @return The registered User object.
     * @throws EmailAlreadyRegisteredException If the email is already registered.
     */
    public User registerUser(User u) throws EmailAlreadyRegisteredException {
        try {
            userService.loadUserByUsername(u.getEmail());
        } catch (UsernameNotFoundException e) {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            return userService.save(u, null);
        }
        throw new EmailAlreadyRegisteredException();
    }

    /**
     * Logs in a user by authenticating the provided credentials and setting the security context.
     *
     * @param u The User object containing login credentials.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     */
    public void loginUser(User u, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(token);
        var sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        request.getSession(true);
        request.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        securityContextRepository.saveContext(sc, request, response);
    }
}
