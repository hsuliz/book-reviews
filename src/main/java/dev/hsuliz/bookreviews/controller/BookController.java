package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.service.BookService;
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .onErrorReturn(BookNotFoundException.class, ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/{id}")
    public Mono<Book> createBookFromAPI(@PathVariable String id) {
        return bookService.createBookFromAPI(id);
    }
}
