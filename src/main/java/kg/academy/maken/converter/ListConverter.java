package kg.academy.maken.converter;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.List;
import kg.academy.maken.model.list_model.ListModel;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListConverter implements BaseConverter<ListModel, List> {
    @Autowired
    private StatusService statusService;
    @Autowired
    private DashboardService dashboardService;

    @Override
    public List convertToEntity(ListModel listModel) {
        if (listModel == null) return null;
        Long id = listModel.getDashboardId();
        Dashboard dashboard = dashboardService.findById(id);
        return List.builder()
                .name(listModel.getName())
                .dashboard(dashboard)
                .status(listModel.getStatusId() != null ? statusService.findById(listModel.getStatusId()) : null)
                .build();
    }

    @Override
    public ListModel convertToModel(List list) {
        if (list == null) return null;
        return ListModel.builder()
                .id(list.getId())
                .name(list.getName())
                .dashboardId(list.getDashboard().getId())
                .statusId(list.getStatus() != null ? list.getStatus().getId() : null)
                .build();
    }
}

