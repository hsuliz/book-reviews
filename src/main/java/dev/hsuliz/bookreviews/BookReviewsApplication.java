package dev.hsuliz.bookreviews;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.model.Review;
import dev.hsuliz.bookreviews.repository.BookRepository;
import dev.hsuliz.bookreviews.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BookReviewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookReviewsApplication.class, args);
    }

    // #TODO DEV DELETE
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository, ReviewRepository reviewRepository) {
        return args -> {
            Mono.zip(bookRepository.deleteAll(), reviewRepository.deleteAll()).block();
            var review = new Review(5, "ds");
            var book = bookRepository.save(new Book("", "", "", "", "")).block();

            Mono.zip(
                            bookRepository.findById(book.id()),
                            reviewRepository.save(review)
                    ).flatMap(objects -> {
                        Book book1 = objects.getT1();
                        Review review1 = objects.getT2();
                        book1.reviews().add(review1);
                        return bookRepository.save(book1);
                    })
                    .subscribe(System.out::println);
            System.out.println("=================");
            System.out.println(bookRepository.findById(book.id()).block());
        };
    }
}
