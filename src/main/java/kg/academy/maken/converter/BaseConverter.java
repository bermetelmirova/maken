package kg.academy.maken.converter;

public interface BaseConverter<M, E> {
    E convertToEntity(M model);

    M convertToModel(E entity);
}
