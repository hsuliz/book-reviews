package dev.hsuliz.bookreviews;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.model.Review;
import dev.hsuliz.bookreviews.repository.BookRepository;
import dev.hsuliz.bookreviews.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
public class BookReviewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookReviewsApplication.class, args);
    }

    // #TODO DEV DELETE
    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository bookRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {
            Review review = new Review(5, "Great book!");
            var x = reviewRepository.save(review).block();
            System.out.println(reviewRepository.findById(x.id()).block());
        };
    }
}
