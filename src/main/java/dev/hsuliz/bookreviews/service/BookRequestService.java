package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.util.dto.BookResponse;
import dev.hsuliz.bookreviews.util.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookRequestService {
    private final BookMapper bookMapper;

    public WebClient webClient = WebClient
            .builder()
            .baseUrl("https://www.dbooks.org/api/book")
            .build();

    public Mono<Book> findById(String id) {
        return webClient
                .get()
                .uri("/"+id)
                .retrieve()
                .bodyToMono(BookResponse.class)
                .map(bookMapper::reponseToModel);
    }
}
