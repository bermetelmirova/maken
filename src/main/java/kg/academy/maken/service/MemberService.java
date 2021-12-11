package kg.academy.maken.service;

import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.model.DashboardMemberModel;

public interface MemberService extends BaseService<DashboardMember>{
    DashboardMemberModel addAdmin(DashboardMemberModel model);

    DashboardMemberModel removeAdmin(DashboardMemberModel model);
}
