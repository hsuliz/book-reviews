package dev.hsuliz.bookreviews.util.component;

import dev.hsuliz.bookreviews.model.Book;
import dev.hsuliz.bookreviews.util.dto.BookResponse;
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException;
import dev.hsuliz.bookreviews.util.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookRequester {
    private final BookMapper bookMapper;

    private final WebClient findBookWebClient;

    public Mono<Book> findById(String id) {
        return findBookWebClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(BookNotFoundException::new)
                )
                .bodyToMono(BookResponse.class)
                .map(bookMapper::responseToModel);
    }
}