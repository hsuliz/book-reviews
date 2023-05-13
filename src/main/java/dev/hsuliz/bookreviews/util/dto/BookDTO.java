package dev.hsuliz.bookreviews.util.dto;

public record BookDTO(
        String id,
        String title,
        String author,
        String year,
        String image
) {
}
