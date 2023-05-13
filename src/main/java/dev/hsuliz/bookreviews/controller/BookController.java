package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.service.BookService;
import dev.hsuliz.bookreviews.util.dto.BookDTO;
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException;
import dev.hsuliz.bookreviews.util.mapper.BookMapper;
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
    private final BookMapper bookMapper;
    private final BookService bookService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BookDTO>> getBookById(@PathVariable String id) {
        return bookService
                .findBookById(id)
                .map(bookMapper::modelToDTO)
                .map(ResponseEntity::ok)
                .onErrorReturn(BookNotFoundException.class, ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<BookDTO> findAllBooks() {
        return bookService
                .findAllBooks()
                .map(bookMapper::modelToDTO);
    }

    @PostMapping("/{id}")
    public Mono<ResponseEntity<BookDTO>> createBookFromAPI(@PathVariable String id) {
        return bookService
                .createBookFromAPI(id)
                .map(bookMapper::modelToDTO)
                .map(ResponseEntity::ok)
                .onErrorReturn(BookNotFoundException.class, ResponseEntity.notFound().build());
    }
}
