package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.DashboardAddMemberModel;
import kg.academy.maken.model.DashboardModel;
import kg.academy.maken.model.DashboardMemberModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DashboardService extends BaseService<Dashboard>, BaseModelService<DashboardModel> {
    DashboardAddMemberModel addMember(DashboardAddMemberModel dashboardAddMemberModel);

}
