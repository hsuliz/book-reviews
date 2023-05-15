package dev.hsuliz.bookreviews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id;
    private Integer star;
    private String comment;
    @CreatedDate
    private Instant dateCreated;

    public Review(Integer star, String comment) {
        this.star = star;
        this.comment = comment;
    }
}