package kg.academy.maken.converter;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.dashboard_model.DashboardModel;
import org.springframework.stereotype.Component;

@Component
public class DashboardConverter implements BaseConverter<DashboardModel, Dashboard> {
    @Override
    public DashboardModel convertToModel(Dashboard dashboard) {
        if (dashboard == null) return null;
        return DashboardModel.builder()
                .ID(dashboard.getId())
                .name(dashboard.getName())
                .build();
    }

    @Override
    public Dashboard convertToEntity(DashboardModel dashboardModel) {
        if (dashboardModel == null) return null;
        return Dashboard.builder()
                .name(dashboardModel.getName())
                .build();
    }
}
