package dev.hsuliz.bookreviews.repository;

import dev.hsuliz.bookreviews.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

// in java, because spring doesn't want to inject kotlin file
@Repository
public interface TestBookRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findBookByExternalId(Mono<String> externalId);
}
