package dev.hsuliz.bookreviews.dto;

public record BookResponse(
        String id,
        String author,
        String title,
        String year,
        String image
) {
}
