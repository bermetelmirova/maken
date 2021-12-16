package kg.academy.maken.converter;

public interface BaseConverter<Model, Entity> {
    public Entity convertToEntity(Model model);

    public Model convertToModel(Entity entity);
}
