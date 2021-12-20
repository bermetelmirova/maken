package kg.academy.maken.service.impl;

import kg.academy.maken.converter.LabelConverter;
import kg.academy.maken.entity.Label;
import kg.academy.maken.model.label_model.LabelModel;
import kg.academy.maken.repository.LabelRepository;
import kg.academy.maken.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelConverter labelConverter;

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

    @Override
    public LabelModel update(LabelModel labelModel) {
        Label label = findById(labelModel.getId());
        if (labelModel.getName() != null) label.setName(labelModel.getName());
        if (labelModel.getColor() != null) label.setColor(labelModel.getColor());
        labelRepository.save(label);
        return labelConverter.convertToModel(label);
    }
}
