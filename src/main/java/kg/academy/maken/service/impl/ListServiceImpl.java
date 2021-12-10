package kg.academy.maken.service.impl;

import kg.academy.maken.entity.List;
import kg.academy.maken.model.ListModel;
import kg.academy.maken.repository.ListRepository;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.ListService;
import kg.academy.maken.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImpl implements ListService {
    private final StatusService statusService;
    private final DashboardService dashboardService;
    private final ListRepository listRepository;

    @Autowired
    public ListServiceImpl(StatusService statusService, @Lazy DashboardService dashboardService, ListRepository listRepository) {
        this.statusService = statusService;
        this.dashboardService = dashboardService;
        this.listRepository = listRepository;
    }

    @Override
    public void defaultLists(Long id) {
        listRepository.save(List.builder()
                .name("TO DO")
                .dashboard(dashboardService.findById(id))
                .status(statusService.findById(1L))
                .build());
        listRepository.save(List.builder()
                .name("IN PROCESS")
                .dashboard(dashboardService.findById(id))
                .status(statusService.findById(2L))
                .build()
        );
        listRepository.save(List.builder()
                .name("DONE")
                .dashboard(dashboardService.findById(id))
                .status(statusService.findById(2L))
                .build());
    }


    @Override
    public List save(List list) {
        return listRepository.save(list);
    }

    @Override
    public java.util.List<List> getAll() {
        return listRepository.findAll();
    }

    @Override
    public List findById(Long id) {
        return listRepository.findById(id).orElse(null);
    }

    @Override
    public List deleteById(Long id) {
        List list = findById(id);
        if (list != null)
            listRepository.deleteById(id);
        return list;
    }





    private List convertToEntity(ListModel listModel) {
        return List.builder()
                .name(listModel.getName())
                .dashboard(dashboardService.findById(listModel.getDashboardId()))
                .build();
    }

    private ListModel convertToModel(List list) {
        return ListModel.builder()
                .name(list.getName())
                .DashboardId(list.getDashboard().getId())
                .build();
    }
}
