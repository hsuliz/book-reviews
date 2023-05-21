package dev.hsuliz.bookreviews.dto;

import dev.hsuliz.bookreviews.model.Review;

public record ReviewRequest(Review review, String bookId) {
}
