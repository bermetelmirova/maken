package kg.academy.maken.service.impl;

import kg.academy.maken.entity.List;
import kg.academy.maken.model.ListModel;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImpl {
    private final StatusService statusService;
    private final DashboardService dashboardService;

    @Autowired
    public ListServiceImpl(StatusService statusService, DashboardService dashboardService) {
        this.statusService = statusService;
        this.dashboardService = dashboardService;
    }

    private List convertToEntity(ListModel listModel){
        return List.builder()
                .name(listModel.getName())
                .dashboard(dashboardService.findById(listModel.getDashboardId()))
                .build();
    }

    private ListModel convertToModel(List list){
        return ListModel.builder()
                .name(list.getName())
                .DashboardId(list.getDashboard().getId())
                .build();
    }
}
