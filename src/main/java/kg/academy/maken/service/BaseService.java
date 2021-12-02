package kg.academy.maken.service;

import java.util.List;

public interface BaseService<T> {
    T save(T t);

    List<T> getAll();

    T findById(Long id);

    T deleteById(Long id);
}
