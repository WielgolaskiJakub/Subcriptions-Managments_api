package com.example.subcriptionsmanagments_api.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super("error.user_not_found" + id);
    }
}
