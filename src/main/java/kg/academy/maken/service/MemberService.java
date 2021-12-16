package kg.academy.maken.service;

import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.model.DashboardMemberModel;

import java.util.List;

public interface MemberService extends BaseService<DashboardMember>{
    DashboardMemberModel addAdmin(DashboardMemberModel model);

    DashboardMemberModel removeAdmin(DashboardMemberModel model);

//    List<DashboardMemberModel> getMembersByDashboard(Long id);
}
