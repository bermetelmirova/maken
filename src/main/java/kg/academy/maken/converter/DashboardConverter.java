package kg.academy.maken.converter;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.DashboardModel;
import org.springframework.stereotype.Component;

@Component
public class DashboardConverter extends BaseConverter<DashboardModel, Dashboard>{
    public DashboardConverter() {
        super(DashboardConverter::convertToEntity, DashboardConverter:: convertToModel);
    }
    private static DashboardModel convertToModel(Dashboard dashboard) {
        if (dashboard == null) return null;
        return  DashboardModel.builder()
                .ID(dashboard.getId())
                .name(dashboard.getName())
                .build();
    }

    private static Dashboard convertToEntity(DashboardModel dashboardModel) {
        if (dashboardModel == null) return null;
        return Dashboard.builder()
                .name(dashboardModel.getName())
                .build();
    }
}
