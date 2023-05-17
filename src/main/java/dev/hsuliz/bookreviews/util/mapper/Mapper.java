package dev.hsuliz.bookreviews.util.mapper;


public interface Mapper<REQUEST, MODEL, RESPONSE> {
    public MODEL responseToModel(REQUEST response);

    public RESPONSE modelToResponse(MODEL model);
}
