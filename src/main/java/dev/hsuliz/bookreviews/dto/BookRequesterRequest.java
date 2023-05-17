package dev.hsuliz.bookreviews.dto;

public record BookRequesterRequest(
        String id,
        String authors,
        String title,
        String year,
        String image
) {
}
