package dev.hsuliz.bookreviews.dto;

import dev.hsuliz.bookreviews.model.Review;

public record ReviewDTO(Review review, String bookId) {
}
