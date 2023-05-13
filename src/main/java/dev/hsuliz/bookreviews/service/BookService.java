package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.util.component.BookRequester;
import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.repository.BookRepository;
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    private final BookRequester bookRequester;

    public Mono<Book> findBookById(String id) {
        return bookRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new BookNotFoundException(id)));
    }

    public Flux<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> createBookFromAPI(String id) {
        return bookRequester
                .findById(id)
                .flatMap(bookRepository::save);
    }
}
