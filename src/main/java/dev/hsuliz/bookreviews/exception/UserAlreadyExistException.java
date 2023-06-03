package dev.hsuliz.bookreviews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "dude")
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super();
    }
}
