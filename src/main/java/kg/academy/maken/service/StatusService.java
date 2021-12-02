package kg.academy.maken.service;

import kg.academy.maken.entity.Status;

import java.util.List;

public interface StatusService {
    Status save(Status status);
    Status deleteById(Long id);
    Status getById(Long id);
    List<Status> getAll();
    Status update(Status status);
}
