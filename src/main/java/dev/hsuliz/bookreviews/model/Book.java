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
    public Book(String author, String title, String externalId) {
        this.author = author;
        this.title = title;
        this.externalId = externalId;
    }
}
