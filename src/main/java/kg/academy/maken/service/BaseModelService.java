package kg.academy.maken.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseModelService <T>{
        T saveModel(T model);

       T deleteModelById(Long id);

       T getModelById(Long id);

        List<T> getAllModel();

        Page<T> getPage(Pageable pageable);

        T update(T model);
}
