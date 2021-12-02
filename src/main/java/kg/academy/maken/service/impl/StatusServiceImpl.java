package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Status;
import kg.academy.maken.repository.StatusRepository;
import kg.academy.maken.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status deleteById(Long id) {
        Status status = findById(id);
        if (status != null)
            statusRepository.deleteById(id);
        return status;
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

}
