package com.example.schooljournal.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class UnauthorizedAction extends RuntimeException {
    public UnauthorizedAction() {
    }

    public UnauthorizedAction(String message) {
        super(message);
    }

    public UnauthorizedAction(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedAction(Throwable cause) {
        super(cause);
    }

    public UnauthorizedAction(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
