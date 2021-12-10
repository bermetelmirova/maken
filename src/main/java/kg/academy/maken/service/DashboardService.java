package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.DashboardModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DashboardService extends BaseService<Dashboard> {
    DashboardModel saveModel(DashboardModel dashboardModel);

    DashboardModel deleteModelById(Long id);

    DashboardModel getModelById(Long id);

    List<DashboardModel> getAllModel();

    Page<DashboardModel> getPage(Pageable pageable);

    DashboardModel update(DashboardModel dashboardModel);
}
