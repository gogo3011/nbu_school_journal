package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.UserRepository;
import com.example.schooljournal.services.exceptions.EmailAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, UserRepository> implements UserDetailsService {

    public UserService(UserRepository repository) {
        super(repository);
    }

    /**
     * Loads a user by their email address, as required by Spring Security.
     *
     * @param email The email address of the user to be loaded.
     * @return The User object corresponding to the specified email address.
     * @throws UsernameNotFoundException If no user with the given email address is found.
     */
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User found = repository.findUserByEmail(email);
        if (null == found) {
            throw new UsernameNotFoundException("No user with " + email + " email found");
        }
        return repository.findUserByEmail(email);
    }

}
