package dev.hsuliz.bookreviews.util.dto;

public record BookResponse(
        String id,
        String authors,
        String title,
        String year,
        String image
) {
}
