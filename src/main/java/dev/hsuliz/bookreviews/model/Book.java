package dev.hsuliz.bookreviews.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "book")
@Data
@NoArgsConstructor
public final class Book {
    @Id
    private String id;
    private String externalId;
    private String author;
    private String title;
    private String year;
    private String image;
    private Collection<Review> reviews;

    public Book(String externalId, String author, String title, String year, String image) {
        this.externalId = externalId;
        this.author = author;
        this.title = title;
        this.year = year;
        this.image = image;
        this.reviews = new ArrayList<>();
    }
}