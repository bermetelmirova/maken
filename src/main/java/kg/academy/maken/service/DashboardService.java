package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.dashboard_model.DashboardAddMemberModel;
import kg.academy.maken.model.dashboard_model.DashboardModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DashboardService extends BaseService<Dashboard>, BaseModelService<DashboardModel> {
    DashboardAddMemberModel addMember(DashboardAddMemberModel dashboardAddMemberModel);
    Page<DashboardModel> getPage(Pageable pageable);
}
