package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.util.dto.BookResponse;
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException;
import dev.hsuliz.bookreviews.util.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookRequestService {
    private final BookMapper bookMapper;

    private final WebClient webClient = WebClient
            .builder()
            .baseUrl("https://www.dbooks.org/api/book")
            .build();

    public Mono<Book> findById(String id) {
        return webClient
                .get()
                .uri("/" + id)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(BookNotFoundException::new)
                )
                .bodyToMono(BookResponse.class)
                .map(bookMapper::reponseToModel);
    }
}
