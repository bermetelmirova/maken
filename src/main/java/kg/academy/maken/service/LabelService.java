package kg.academy.maken.service;

import kg.academy.maken.entity.Label;
import kg.academy.maken.model.label_model.LabelModel;

public interface LabelService extends BaseService<Label> {
    LabelModel update(LabelModel labelModel);
}
