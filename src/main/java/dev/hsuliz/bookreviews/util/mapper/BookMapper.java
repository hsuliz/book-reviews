package dev.hsuliz.bookreviews.util.mapper;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.util.dto.BookDTO;
import dev.hsuliz.bookreviews.util.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookResponse, Book, BookDTO> {
    @Override
    public Book responseToModel(BookResponse bookResponse) {
        return new Book(
                bookResponse.id(),
                bookResponse.authors(),
                bookResponse.title(),
                bookResponse.year(),
                bookResponse.image()
        );
    }

    @Override
    public BookDTO modelToDTO(Book book) {
        return new BookDTO(
                book.id(),
                book.author(),
                book.title(),
                book.year(),
                book.image()
        );
    }
}
