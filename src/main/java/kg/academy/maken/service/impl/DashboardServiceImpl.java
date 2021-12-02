package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.model.DashboardModel;
import kg.academy.maken.repository.DashboardRepository;
import kg.academy.maken.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Dashboard save(Dashboard dashboard) {
        return null;
    }

    @Override
    public List<Dashboard> getAll() {
        return null;
    }


    @Override
    public Dashboard deleteById(Long id) {
        return null;
    }

    @Override
    public DashboardModel saveModel(DashboardModel dashboardModel) {
        return null;
    }

    @Override
    public DashboardModel deleteModelById(Long id) {
        return null;
    }

    @Override
    public DashboardModel getModelById(Long id) {
        return null;
    }

    @Override
    public List<DashboardModel> getAllModel() {
        return null;
    }

    @Override
    public DashboardModel update(DashboardModel dashboardModel) {
        return null;
    }

    @Override
    public Dashboard findById(Long id) {
        return dashboardRepository.findById(id).orElse(null);
    }
    private Dashboard convertToEntity(DashboardModel dashboardModel){
        return Dashboard.builder()
                .name(dashboardModel.getName())
                .build();
    }

    private DashboardModel convertToModel(Dashboard dashboard){
        return  DashboardModel.builder()
                .name(dashboard.getName())
                .build();
    }
}
