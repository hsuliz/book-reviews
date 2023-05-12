package dev.hsuliz.bookreviews.util.exception;

public class BookNotFoundException extends IllegalStateException {

    public BookNotFoundException(String bookId) {
        super("Book with " + bookId + " id not found!!");
    }
}
