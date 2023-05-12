package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public Mono<?> getBookById(@PathVariable String id) {
        return bookService
                .findBookById(id)
                .map(ResponseEntity::ok)
                .doOnError(throwable -> log.error("Book not found"))
                .onErrorReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
