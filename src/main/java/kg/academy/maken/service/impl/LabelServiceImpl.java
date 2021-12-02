package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Label;
import kg.academy.maken.repository.LabelRepository;
import kg.academy.maken.service.LabelService;

import java.util.List;

public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label save(Label label) {
       return labelRepository.save(label);
    }

    @Override
    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label findById(Long id) {
        return labelRepository.findById(id).orElse(null);
    }

    @Override
    public Label deleteById(Long id) {
        Label label = findById(id);
        if (label != null)
            labelRepository.deleteById(id);
        return label;
    }
}
