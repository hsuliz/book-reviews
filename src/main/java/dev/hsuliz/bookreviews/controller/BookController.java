package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.dto.BookResponse;
import dev.hsuliz.bookreviews.service.BookService;
import dev.hsuliz.bookreviews.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookMapper bookMapper;

    private final BookService bookService;

    @GetMapping("/{id}")
    public Mono<BookResponse> getBookById(@PathVariable String id) {
        return bookService
                .findBookById(id)
                .map(bookMapper::modelToResponse);
    }

    @GetMapping
    public Flux<BookResponse> findAllBooks() {
        return bookService
                .findAllBooks()
                .map(bookMapper::modelToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createBookFromAPI(@RequestBody String id) {
        return bookService
                .saveBookFromAPI(id)
                .then();
    }
}
