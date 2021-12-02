package kg.academy.maken.converter;

import kg.academy.maken.entity.List;
import kg.academy.maken.model.ListModel;
import org.springframework.stereotype.Component;

@Component
public class ListConverter extends BaseConverter<ListModel, List>{
    public ListConverter() {
        super(ListConverter::convertToEntity, ListConverter::convertToModel);
    }

    private static ListModel convertToModel(List list) {
        if (list == null) return null;
        return ListModel.builder()
                .ID(list.getId())
                .name(list.getName())
                .DashboardId(list.getDashboard().getId())
                .build();
    }

    private static List convertToEntity(ListModel listModel) {
        if (listModel == null) return null;
        return List.builder()
                .name(listModel.getName())
                .build();
    }
}

