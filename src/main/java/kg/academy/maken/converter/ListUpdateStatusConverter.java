package kg.academy.maken.converter;

import kg.academy.maken.entity.List;
import kg.academy.maken.model.ListStatusUpdateModel;
import kg.academy.maken.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListUpdateStatusConverter implements BaseConverter<ListStatusUpdateModel, List> {
    private final StatusService statusService;
    @Override
    public List convertToEntity(ListStatusUpdateModel listStatusUpdateModel) {
        if (listStatusUpdateModel == null) return null;
        return List.builder()
                .status(statusService.findById(listStatusUpdateModel.getStatusId()))
                .build();
    }

    @Override
    public ListStatusUpdateModel convertToModel(List list) {
        if (list == null) return null;
        return ListStatusUpdateModel.builder()
                .id(list.getId())
                .statusId(list.getStatus().getId())
                .build();
    }
}
