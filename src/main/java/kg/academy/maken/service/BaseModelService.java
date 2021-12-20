package kg.academy.maken.service;


import java.util.List;

public interface BaseModelService<T> {
    T saveModel(T model);

    T deleteModelById(Long id);

    T getModelById(Long id);

    List<T> getAllModel();

    T update(T model);
}
