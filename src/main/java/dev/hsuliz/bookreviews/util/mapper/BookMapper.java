package dev.hsuliz.bookreviews.util.mapper;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.util.dto.BookDTO;
import dev.hsuliz.bookreviews.util.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookResponse, Book, BookDTO> {
    @Override
    public Book reponseToModel(BookResponse bookResponse) {
        return new Book(
                bookResponse.id(),
                bookResponse.title(),
                bookResponse.authors(),
                bookResponse.year(),
                bookResponse.image()
        );
    }

    @Override
    public BookDTO modelToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getImage()
        );
    }
}
