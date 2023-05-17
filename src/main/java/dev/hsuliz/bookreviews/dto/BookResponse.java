package dev.hsuliz.bookreviews.dto;

import dev.hsuliz.bookreviews.model.Review;

import java.util.Collection;

public record BookResponse(
        String id,
        String author,
        String title,
        String year,
        String image,
        Collection<Review> reviews
) {
}
