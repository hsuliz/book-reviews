package dev.hsuliz.bookreviews.mapper;

import dev.hsuliz.bookreviews.dto.BookRequesterRequest;
import dev.hsuliz.bookreviews.dto.BookResponse;
import dev.hsuliz.bookreviews.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookRequesterRequest, Book, BookResponse> {
    @Override
    public Book responseToModel(BookRequesterRequest bookRequesterRequest) {
        return new Book(
                bookRequesterRequest.id(),
                bookRequesterRequest.authors(),
                bookRequesterRequest.title(),
                bookRequesterRequest.year(),
                bookRequesterRequest.image()
        );
    }

    @Override
    public BookResponse modelToResponse(Book book) {
        return new BookResponse(
                book.id(),
                book.author(),
                book.title(),
                book.year(),
                book.image(),
                book.reviews()
        );
    }
}
