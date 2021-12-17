package kg.academy.maken.service.impl;

import kg.academy.maken.converter.DashboardConverter;
import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.entity.User;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.dashboard_model.DashboardAddMemberModel;
import kg.academy.maken.model.dashboard_model.DashboardModel;
import kg.academy.maken.repository.DashboardRepository;
import kg.academy.maken.service.DashboardMemberService;
import kg.academy.maken.service.DashboardService;
import kg.academy.maken.service.ListService;
import kg.academy.maken.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private DashboardConverter dashboardConverter;
    @Autowired
    private DashboardMemberService memberService;
    @Autowired
    private UserService userService;
    @Autowired
    private ListService listService;

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
        return dashboardRepository.findById(id)
                .orElseThrow(() -> new ApiException("Доска не найдена", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Dashboard deleteById(Long id) {
        Dashboard dashboard = findById(id);
        if (dashboard == null)
            throw new ApiException("Доска не найдена!", HttpStatus.BAD_REQUEST);
        dashboardRepository.deleteById(id);
        return dashboard;
    }

    @Override
    public DashboardModel saveModel(DashboardModel dashboardModel) {
        Dashboard dashboard = dashboardConverter.convertToEntity(dashboardModel);
        dashboardRepository.save(dashboard);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        memberService.save(new DashboardMember(user, dashboard, true));
        listService.defaultLists(dashboard);
        return dashboardModel;
    }

    @Override
    public DashboardModel deleteModelById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        if (!memberService.isAdmin(user, id))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
        return dashboardConverter.convertToModel(deleteById(id));
    }

    @Override
    public DashboardModel getModelById(Long id) {
        return dashboardConverter.convertToModel(findById(id));
    }

    @Override
    public List<DashboardModel> getAllModel() {
        List<Dashboard> dashboards = dashboardRepository.findAll();
        return dashboards.stream()
                .map(dashboardConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DashboardModel> getPage(Pageable pageable) {
        Page<Dashboard> dashboards = dashboardRepository.findAll(pageable);
        return dashboards
                .map(dashboardConverter::convertToModel);
    }

    @Override
    public DashboardModel update(DashboardModel dashboardModel) {
        Dashboard dashboardForUpdate = findById(dashboardModel.getID());
        if (dashboardModel.getName() != null) dashboardForUpdate.setName(dashboardModel.getName());
        dashboardRepository.save(dashboardForUpdate);
        return dashboardConverter.convertToModel(dashboardForUpdate);
    }

    @Override
    public DashboardAddMemberModel addMember(DashboardAddMemberModel dashboardAddMemberModel) {
        User user = userService.getByLogin(dashboardAddMemberModel.getLogin());
        Dashboard dashboard = findById(dashboardAddMemberModel.getId());
        memberService.save(new DashboardMember(user, dashboard, false));
        return dashboardAddMemberModel;
    }
}
