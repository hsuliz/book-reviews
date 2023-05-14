package dev.hsuliz.bookreviews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    private String id;
    private String externalId;
    private String author;
    private String title;
    private String year;
    private String image;

    public Book(String externalId, String author, String title, String year, String image) {
        this.externalId = externalId;
        this.author = author;
        this.title = title;
        this.year = year;
        this.image = image;
    }
}
