package dev.hsuliz.bookreviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.TreeSet;

@Document(collection = "book")
public record Book(@Id String id, String externalId, String author, String title, String year, String image,
                   Collection<Review> reviews) {
    public Book(String externalId, String author, String title, String year, String image) {
        this(null, externalId, author, title, year, image, new TreeSet<>());
    }
}

