package dev.hsuliz.bookreviews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends IllegalStateException {
    public BookNotFoundException(String bookId) {
        super("Book with " + bookId + " id not found!!");
    }
}
