package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.model.Review;
import dev.hsuliz.bookreviews.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book/{id}/review")
@RequiredArgsConstructor
public class BookReviewController {
    private final BookReviewService bookReviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createReviewForGivenBook(@PathVariable("id") String bookId, @RequestBody Review review) {
        return bookReviewService
                .addReviewForGivenBook(bookId, review)
                .then();
    }
}
