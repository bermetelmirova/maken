package kg.academy.maken.converter;

import kg.academy.maken.entity.Label;
import kg.academy.maken.entity.User;
import kg.academy.maken.model.LabelModel;
import kg.academy.maken.model.UserModel;

import java.util.function.Function;

public class LabelConverter extends BaseConverter<LabelModel, Label>{

    public LabelConverter() {
        super(LabelConverter::convertToEntity, LabelConverter::convertToModel);
    }

    private static LabelModel convertToModel(Label label) {
        if (label == null) return null;
      return LabelModel.builder()
              .ID(label.getId())
              .name(label.getName())
              .color(label.getColor())
              .build();
    }

    private static Label convertToEntity(LabelModel labelModel) {
        if (labelModel == null) return null;
       return Label.builder()
               .name(labelModel.getName())
               .color(labelModel.getColor())
               .build();
    }
}
