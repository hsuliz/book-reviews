package dev.hsuliz.bookreviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String title;

    private String author;

    private String year;

    private String image;

    public Book(String externalId, String title, String author, String year, String image) {
        this.externalId = externalId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.image = image;
    }
}
