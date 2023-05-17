package dev.hsuliz.bookreviews.util.mapper;


public interface Mapper<REQUEST, MODEL, RESPONSE> {
    MODEL responseToModel(REQUEST response);

    RESPONSE modelToResponse(MODEL model);
}
