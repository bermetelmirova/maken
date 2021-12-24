package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.dashboard_model.DashboardAddMemberModel;
import kg.academy.maken.model.dashboard_model.DashboardModel;
import kg.academy.maken.model.list_model.ListGetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DashboardService extends BaseService<Dashboard>, BaseModelService<DashboardModel> {
    DashboardAddMemberModel addMember(DashboardAddMemberModel dashboardAddMemberModel);
    Page<DashboardModel> getPage(Pageable pageable);
    List<ListGetModel> getListByDashboard(Long id);
    List<DashboardModel> getByUser();
}
