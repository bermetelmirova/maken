package kg.academy.maken.service;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.DashboardModel;

import java.util.List;

public interface DashboardService extends BaseService<Dashboard>{
    DashboardModel saveModel(DashboardModel dashboardModel);
    DashboardModel deleteModelById(Long id);
    DashboardModel getModelById(Long id);
    List<DashboardModel> getAllModel();
    DashboardModel update(DashboardModel dashboardModel);
}
