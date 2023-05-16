package dev.hsuliz.bookreviews.util.mapper;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.dto.BookResponse;
import dev.hsuliz.bookreviews.dto.BookRequesterResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookRequesterResponse, Book, BookResponse> {
    @Override
    public Book responseToModel(BookRequesterResponse bookRequesterResponse) {
        return new Book(
                bookRequesterResponse.id(),
                bookRequesterResponse.authors(),
                bookRequesterResponse.title(),
                bookRequesterResponse.year(),
                bookRequesterResponse.image()
        );
    }

    @Override
    public BookResponse modelToDTO(Book book) {
        return new BookResponse(
                book.id(),
                book.author(),
                book.title(),
                book.year(),
                book.image()
        );
    }
}
