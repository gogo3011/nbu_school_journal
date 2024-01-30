package com.example.schooljournal.web.endpoints;

import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class GenericEndpoint {

    @Autowired
    protected UserService userService;

    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.loadUserByUsername(currentUserName);
        }
        return null;
    }

    protected Pageable mapToPage(Integer pageNo,
                             Integer pageSize,
                             String sortBy) {
        return PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    }
}
