package kg.academy.maken.converter;

import kg.academy.maken.entity.Label;
import kg.academy.maken.model.label_model.LabelModel;

import org.springframework.stereotype.Component;

@Component
public class LabelConverter implements BaseConverter<LabelModel, Label> {
    @Override
    public LabelModel convertToModel(Label label) {
        if (label == null) return null;
        return LabelModel.builder()
                .id(label.getId())
                .name(label.getName())
                .color(label.getColor())
                .build();
    }

    @Override
    public Label convertToEntity(LabelModel labelModel) {
        if (labelModel == null) return null;
        return Label.builder()
                .name(labelModel.getName())
                .color(labelModel.getColor())
                .build();
    }
}
