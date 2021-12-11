package kg.academy.maken.service.impl;

import kg.academy.maken.converter.DashboardConverter;
import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.entity.User;
import kg.academy.maken.model.DashboardAddMemberModel;
import kg.academy.maken.model.DashboardModel;
import kg.academy.maken.repository.DashboardRepository;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.MemberService;
import kg.academy.maken.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final DashboardRepository dashboardRepository;
    private final DashboardConverter dashboardConverter;
    private final MemberService memberService;
    private final UserService userService;

    @Override
    public Dashboard save(Dashboard dashboard) {
        return dashboardRepository.save(dashboard);
    }

    @Override
    public List<Dashboard> getAll() {
        return dashboardRepository.findAll();
    }

    @Override
    public Dashboard findById(Long id) {
        return dashboardRepository.findById(id).orElse(null);
    }

    @Override
    public Dashboard deleteById(Long id) {
        Dashboard dashboard = findById(id);
        if (dashboard != null)
            dashboardRepository.deleteById(id);
        return dashboard;
    }

    @Override
    public DashboardModel saveModel(DashboardModel dashboardModel) {
        Dashboard dashboard = dashboardConverter.convertFromModel(dashboardModel);
        dashboardRepository.save(dashboard);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        memberService.save(new DashboardMember(user, dashboard, true));
        return dashboardModel;
    }

    @Override
    public DashboardModel deleteModelById(Long id) {
        return dashboardConverter.convertFromEntity(deleteById(id));
    }

    @Override
    public DashboardModel getModelById(Long id) {
        return dashboardConverter.convertFromEntity(findById(id));
    }

    @Override
    public List<DashboardModel> getAllModel() {
        List<Dashboard> dashboards = dashboardRepository.findAll();
        return dashboards.stream()
                .map(dashboardConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DashboardModel> getPage(Pageable pageable) {
        Page<Dashboard> dashboards = dashboardRepository.findAll(pageable);
        return dashboards
                .map(dashboardConverter::convertFromEntity);
    }

    @Override
    public DashboardModel update(DashboardModel dashboardModel) {
        Dashboard dashboardForUpdate = findById(dashboardModel.getID());
        if (dashboardModel.getName() != null) dashboardForUpdate.setName(dashboardModel.getName());
        dashboardRepository.save(dashboardForUpdate);
        return dashboardConverter.convertFromEntity(dashboardForUpdate);
    }

    @Override
    public DashboardAddMemberModel addMember(DashboardAddMemberModel dashboardAddMemberModel) {
        User user = userService.getByLogin(dashboardAddMemberModel.getLogin());
        Dashboard dashboard = findById(dashboardAddMemberModel.getId());
        memberService.save(new DashboardMember(user, dashboard, false));
        return dashboardAddMemberModel;
    }
}
