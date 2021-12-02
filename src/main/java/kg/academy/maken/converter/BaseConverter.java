package kg.academy.maken.converter;

import java.util.function.Function;

public class BaseConverter<Model, Entity> {
    private Function<Model, Entity> fromModel;
    private Function<Entity, Model> fromEntity;

    public BaseConverter(Function<Model, Entity> fromModel, Function<Entity, Model> fromEntity) {
        this.fromModel = fromModel;
        this.fromEntity = fromEntity;
    }

    public final Entity convertFromModel(Model modelToConvert) {
        return fromModel.apply(modelToConvert);
    }

    public final Model convertFromEntity(Entity entityToConvert) {
        return fromEntity.apply(entityToConvert);
    }
}