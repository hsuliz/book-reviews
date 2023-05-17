package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.model.Review;
import dev.hsuliz.bookreviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

// #TODO Need to add review service

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private final ReviewRepository reviewRepository;

    private final BookService bookService;

    public Mono<Void> addReviewForGivenBook(String bookId, Review review) {
        return bookService
                .findBookById(bookId)
                .flatMap(foundBook -> reviewRepository.save(review)
                        .flatMap(savedReview -> {
                            foundBook.reviews().add(savedReview);
                            return bookService.saveBook(foundBook).then();
                        }))
                .then();
    }
}
