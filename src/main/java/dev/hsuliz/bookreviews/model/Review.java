package dev.hsuliz.bookreviews.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;


@Document(collection = "review")
public final class Review implements Comparable<Review> {
    @Id
    private final String id;

    private final Integer star;

    private final String comment;

    @CreatedDate
    @JsonProperty("date-created")
    private final Instant dateCreated;

    public Review(
            String id,
            Integer star,
            String comment,
            @JsonProperty("date-created")
            Instant dateCreated) {
        this.id = id;
        this.star = star;
        this.comment = comment;
        this.dateCreated = dateCreated;
    }

    public Review(Integer star, String comment) {
        this(null, star, comment, null);
    }

    @Override
    public int compareTo(@NotNull Review o) {
        return dateCreated.compareTo(o.dateCreated);
    }

    @Id
    public String id() {
        return id;
    }

    public Integer star() {
        return star;
    }

    public String comment() {
        return comment;
    }

    @CreatedDate
    @JsonProperty("date-created")
    public Instant dateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Review) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.star, that.star) &&
                Objects.equals(this.comment, that.comment) &&
                Objects.equals(this.dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, star, comment, dateCreated);
    }

    @Override
    public String toString() {
        return "Review[" +
                "id=" + id + ", " +
                "star=" + star + ", " +
                "comment=" + comment + ", " +
                "dateCreated=" + dateCreated + ']';
    }
}