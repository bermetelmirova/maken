package kg.academy.maken.converter;

import kg.academy.maken.entity.List;
import kg.academy.maken.model.list_model.ListNameUpdateModel;

import org.springframework.stereotype.Component;

@Component
public class ListNameUpdateConverter implements BaseConverter<ListNameUpdateModel, List> {
    @Override
    public List convertToEntity(ListNameUpdateModel listStatusUpdateModel) {
        if (listStatusUpdateModel == null) return null;
        return List.builder()
                .name(listStatusUpdateModel.getName())
                .build();
    }

    @Override
    public ListNameUpdateModel convertToModel(List list) {
        if (list == null) return null;
        return ListNameUpdateModel.builder()
                .id(list.getId())
                .name(list.getName())
                .build();
    }
}
