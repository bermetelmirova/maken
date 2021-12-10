package kg.academy.maken.controller;

import kg.academy.maken.model.DashboardModel;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.ListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public List<DashboardModel> getAll(){
        return dashboardService.getAllModel();
    }

    @GetMapping("/pageable")
    public Page<DashboardModel> getAllPage(Pageable pageable){
        return dashboardService.getPage(pageable);
    }

    @PostMapping
    public DashboardModel save(@RequestBody DashboardModel dashboardModel){
        return  dashboardService.saveModel(dashboardModel);
    }

    @GetMapping("/{id}")
    public DashboardModel getById(@PathVariable Long id){
        return dashboardService.getModelById(id);
    }

    @PutMapping
    public DashboardModel update(@RequestBody DashboardModel dashboardModel){
        return dashboardService.update(dashboardModel);
    }

    @DeleteMapping("/{id}")
    public DashboardModel deleteById(@PathVariable Long id){
        return dashboardService.deleteModelById(id);
    }
}
