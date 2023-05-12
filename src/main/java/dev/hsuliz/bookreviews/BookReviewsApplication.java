package dev.hsuliz.bookreviews;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewsApplication.class, args);
    }
}
