package dev.hsuliz.bookreviews.controller;

import dev.hsuliz.bookreviews.dto.ReviewRequest;
import dev.hsuliz.bookreviews.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createReviewForGivenBook(@RequestBody ReviewRequest reviewDTO) {
        return reviewService
                .addReviewForGivenBook(reviewDTO.review(), reviewDTO.bookId())
                .then();
    }
}
