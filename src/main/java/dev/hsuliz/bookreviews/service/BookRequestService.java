package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BookRequestService {

    public WebClient webClient = WebClient
            .builder()
            .baseUrl("https://www.dbooks.org/api/book")
            .build();

    public Mono<Book> findBookById(String id) {
        return webClient
                .get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(Book.class);
    }
}
