package dev.hsuliz.bookreviews.dto;

public record BookRequesterResponse(
        String id,
        String authors,
        String title,
        String year,
        String image
) {
}
