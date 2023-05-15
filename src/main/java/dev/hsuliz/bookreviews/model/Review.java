package dev.hsuliz.bookreviews.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection = "review")
public record Review(@Id String id, Integer star, String comment, @CreatedDate Instant dateCreated) {
    public Review(Integer star, String comment) {
        this(null, star, comment, null);
    }
}