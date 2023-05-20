package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.dto.ReviewDTO;
import dev.hsuliz.bookreviews.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final BookService bookService;

    public Mono<Void> addReviewForGivenBook(ReviewDTO reviewDTO) {
        return bookService
                .findBookById(reviewDTO.bookId())
                .flatMap(foundBook -> reviewRepository.save(reviewDTO.review())
                        .flatMap(savedReview -> {
                            foundBook.getReviews().add(savedReview);
                            return bookService.saveBook(foundBook).then();
                        }))
                .then();
    }
}
