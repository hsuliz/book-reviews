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

    // #TODO DEV DELETE
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            repository.deleteAll().block();
            var x = repository.save(new Book("Sasha", "Forest", "123", "1", "1")).block();
            var y = repository.save(new Book("Dima", "Prison", "567", "1", "1")).block();
            System.out.println(x);
            System.out.println(y);
        };
    }
}
