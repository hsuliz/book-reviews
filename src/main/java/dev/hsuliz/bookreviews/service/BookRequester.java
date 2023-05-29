package dev.hsuliz.bookreviews.service;

import dev.hsuliz.bookreviews.dto.BookRequesterRequest;
import dev.hsuliz.bookreviews.exception.BookNotFoundException;
import dev.hsuliz.bookreviews.mapper.BookMapper;
import dev.hsuliz.bookreviews.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
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
                        response -> Mono.error(new BookNotFoundException(id))
                )
                .bodyToMono(BookRequesterRequest.class)
                .map(bookMapper::responseToModel);
    }
}
