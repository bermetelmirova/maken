package kg.academy.maken.controller;

import kg.academy.maken.model.ResponseMessage;
import kg.academy.maken.model.dashboard_model.DashboardAddMemberModel;
import kg.academy.maken.model.dashboard_model.DashboardModel;
import kg.academy.maken.model.list_model.ListGetModel;
import kg.academy.maken.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public List<DashboardModel> getAll() {
        return dashboardService.getAllModel();
    }

    @GetMapping("/pageable")
    public Page<DashboardModel> getAllPage(Pageable pageable) {
        return dashboardService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseMessage<DashboardModel> getById(@PathVariable Long id) {
        return new ResponseMessage<DashboardModel>().prepareSuccessMessage(dashboardService.getModelById(id));
    }

    @GetMapping("/get-all/{id}")
    public List<ListGetModel> getModelWithCard(@PathVariable Long id) {
        return dashboardService.getListByDashboard(id);
    }

    @GetMapping("/get-user-dashboards")
    public List<DashboardModel> getModelWithCard() {
        return dashboardService.getByUser();
    }

    @PostMapping
    public ResponseMessage<DashboardModel> save(@RequestBody DashboardModel dashboardModel) {
        return new ResponseMessage<DashboardModel>().prepareSuccessMessage(dashboardService.saveModel(dashboardModel));
    }

    @PostMapping("/add-member")
    public ResponseMessage<DashboardAddMemberModel> addUser(@RequestBody DashboardAddMemberModel dashboardAddMemberModel) {
        return new ResponseMessage<DashboardAddMemberModel>().prepareSuccessMessage(dashboardService.addMember(dashboardAddMemberModel));
    }

    @PutMapping
    public ResponseMessage<DashboardModel> update(@RequestBody DashboardModel dashboardModel) {
        return new ResponseMessage<DashboardModel>().prepareSuccessMessage(dashboardService.update(dashboardModel));
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<DashboardModel> deleteById(@PathVariable Long id) {
        return new ResponseMessage<DashboardModel>().prepareSuccessMessage(dashboardService.deleteModelById(id));
    }
}
