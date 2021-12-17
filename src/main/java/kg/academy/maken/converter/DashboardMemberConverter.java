package kg.academy.maken.converter;

import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.model.dashboard_model.DashboardMemberModel;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class DashboardMemberConverter implements BaseConverter<DashboardMemberModel, DashboardMember> {
    @Autowired
    private UserService userService;
    @Autowired
    private DashboardService dashboardService;

    @Override
    public DashboardMember convertToEntity(DashboardMemberModel dashboardMemberModel) {
        return DashboardMember.builder()
                .dashboard(dashboardService.findById(dashboardMemberModel.getDashboardId()))
                .user(userService.findById(dashboardMemberModel.getUserId()))
                .build();
    }

    @Override
    public DashboardMemberModel convertToModel(DashboardMember dashboardMember) {
        return DashboardMemberModel.builder()
                .dashboardId(dashboardMember.getId())
                .dashboardId(dashboardMember.getDashboard().getId())
                .userId(dashboardMember.getUser().getId())
                .build();
    }
}
