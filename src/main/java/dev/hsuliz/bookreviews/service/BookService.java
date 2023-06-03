package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.exception.BookNotFoundException;
import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    private final BookRequester requester;

    public Mono<Book> findBookById(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new BookNotFoundException(id)));
    }

    public Flux<Book> findAllBooks() {
        return repository.findAll();
    }

    public Mono<Void> saveBook(Book book) {
        return repository.save(book).then();
    }

    public Mono<Book> saveBookFromAPI(String id) {
        return requester
                .findById(id)
                .flatMap(repository::save);
    }
}
