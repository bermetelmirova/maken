package kg.academy.maken.controller;

import kg.academy.maken.model.DashboardAddMemberModel;
import kg.academy.maken.model.DashboardModel;
import kg.academy.maken.service.DashboardService;

import kg.academy.maken.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public List<DashboardModel> getAll(){
        return dashboardService.getAllModel();
    }

    @GetMapping("/pageable")
    public Page<DashboardModel> getAllPage(Pageable pageable){
        return dashboardService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public DashboardModel getById(@PathVariable Long id){
        return dashboardService.getModelById(id);
    }

    @PostMapping
    public DashboardModel save(@RequestBody DashboardModel dashboardModel){
        return  dashboardService.saveModel(dashboardModel);
    }

    @PostMapping("/add-member")
    public DashboardAddMemberModel addUser(DashboardAddMemberModel dashboardAddMemberModel){
        return dashboardService.addMember(dashboardAddMemberModel);
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
