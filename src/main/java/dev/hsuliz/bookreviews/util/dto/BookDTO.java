package dev.hsuliz.bookreviews.util.dto;

public record BookDTO(
        String id,
        String author,
        String title,
        String year,
        String image
) {
}
