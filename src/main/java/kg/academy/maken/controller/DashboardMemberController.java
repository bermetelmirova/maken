package kg.academy.maken.controller;

import kg.academy.maken.model.ResponseMessage;
import kg.academy.maken.model.dashboard_model.DashboardMemberModel;
import kg.academy.maken.model.user_model.UserNameModel;
import kg.academy.maken.service.DashboardMemberService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class DashboardMemberController {
    @Autowired
    private DashboardMemberService memberService;

    @GetMapping("/dashboard/{id}")
    public List<UserNameModel> getMembersByDashboard(@PathVariable Long id){
        return memberService.getMembersByDashboard(id);
    }

    @DeleteMapping("/remove-member")
    public ResponseMessage<DashboardMemberModel> removeDashboardMember(@RequestBody DashboardMemberModel dashboardMemberModel){
        return new ResponseMessage<DashboardMemberModel>()
                .prepareSuccessMessage(memberService.removeMember(memberService.removeMember(dashboardMemberModel)));
    }

    @PutMapping("/remove-admin")
    public ResponseMessage<DashboardMemberModel> removeAdmin(@RequestBody DashboardMemberModel dashboardMemberModel){
        return new ResponseMessage<DashboardMemberModel>()
                .prepareSuccessMessage(memberService.removeAdmin(dashboardMemberModel));
    }

    @PutMapping("/add-admin")
    public ResponseMessage<DashboardMemberModel> addAdmin(@RequestBody DashboardMemberModel dashboardMemberModel){
        return new ResponseMessage<DashboardMemberModel>()
                .prepareSuccessMessage(memberService.addAdmin(dashboardMemberModel));
    }

}
