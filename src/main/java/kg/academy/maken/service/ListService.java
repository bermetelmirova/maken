package kg.academy.maken.service;

import kg.academy.maken.entity.List;

public interface ListService extends BaseService<List>{
    void defaultLists(Long id);
}
