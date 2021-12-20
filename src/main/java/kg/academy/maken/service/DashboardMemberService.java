package kg.academy.maken.service;

import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.model.dashboard_model.DashboardMemberModel;
import kg.academy.maken.model.user_model.UserNameModel;

import java.util.List;

public interface DashboardMemberService extends BaseService<DashboardMember> {
    DashboardMemberModel addAdmin(DashboardMemberModel model);

    DashboardMemberModel removeAdmin(DashboardMemberModel model);

    DashboardMemberModel removeMember(DashboardMemberModel model);

    List<UserNameModel> getMembersByDashboard(Long id);

    void isAdmin(Long dashboard);
}
