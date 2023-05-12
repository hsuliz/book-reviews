package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }
}
