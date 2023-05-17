package dev.hsuliz.bookreviews.repository;

import dev.hsuliz.bookreviews.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {

}
