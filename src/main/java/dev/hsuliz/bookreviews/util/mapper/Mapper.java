package dev.hsuliz.bookreviews.util.mapper;


public interface Mapper<RESPONSE, MODEL, DTO> {
    public MODEL reponseToModel(RESPONSE response);

    public DTO modelToDTO(MODEL model);
}
