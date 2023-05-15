package dev.hsuliz.bookreviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public record Book(@Id String id, String externalId, String author, String title, String year, String image) {
    public Book(String externalId, String author, String title, String year, String image) {
        this(null, externalId, author, title, year, image);
    }
}

