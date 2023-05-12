package dev.hsuliz.bookreviews.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class Book {
    @Id
    private String id;
    private String author;
    private String title;
    private String isbn;
}
