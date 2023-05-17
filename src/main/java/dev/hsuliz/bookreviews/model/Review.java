package dev.hsuliz.bookreviews.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection = "review")
public record Review(@Id String id, Integer star, String comment,
                     @CreatedDate Instant dateCreated) implements Comparable<Review> {
    public Review(Integer star, String comment) {
        this(null, star, comment, null);
    }

    @Override
    public int compareTo(@NotNull Review o) {
        return dateCreated.compareTo(o.dateCreated);
    }
}