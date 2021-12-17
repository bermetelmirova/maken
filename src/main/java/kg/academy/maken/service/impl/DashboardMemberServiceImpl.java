package kg.academy.maken.service.impl;

import kg.academy.maken.converter.UserNameConverter;
import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.entity.User;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.dashboard_model.DashboardMemberModel;
import kg.academy.maken.model.user_model.UserNameModel;
import kg.academy.maken.repository.DashboardMemberRepository;

import kg.academy.maken.service.DashboardMemberService;

import kg.academy.maken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardMemberServiceImpl implements DashboardMemberService {
    @Autowired
    private DashboardMemberRepository memberRepository;
    @Autowired
    private UserNameConverter userNameConverter;
    @Autowired
    private UserService userService;

    @Override
    public DashboardMember save(DashboardMember dashboardMember) {
        return memberRepository.save(dashboardMember);
    }

    @Override
    public List<DashboardMember> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public DashboardMember findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ApiException("Участник не найден", HttpStatus.BAD_REQUEST));
    }

    @Override
    public DashboardMember deleteById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        DashboardMember dashboardMember = findById(id);
        if (!isAdmin(user, dashboardMember.getDashboard().getId()))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
        if (dashboardMember == null)
            throw new ApiException("Участник не найден!", HttpStatus.BAD_REQUEST);
        memberRepository.deleteById(id);
        return dashboardMember;
    }

    @Override
    public DashboardMemberModel addAdmin(DashboardMemberModel model) {
        DashboardMember dashboardMember = findById(model.getDashboardId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        if (!isAdmin(user, model.getDashboardId()))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
        if (dashboardMember != null)
            dashboardMember.setIsAdmin(true);
        save(dashboardMember);
        return model;
    }

    @Override
    public DashboardMemberModel removeAdmin(DashboardMemberModel model) {
        DashboardMember dashboardMember = findById(model.getDashboardId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        if (!isAdmin(user, model.getDashboardId()))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
        if (dashboardMember != null) dashboardMember.setIsAdmin(false);
        save(dashboardMember);
        return model;
    }

    @Override
    public DashboardMemberModel removeMember(DashboardMemberModel model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userCheck = userService.getByLogin(userName);
        if (!isAdmin(userCheck, model.getDashboardId()))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
        User user = userService.findById(model.getUserId());
        if (isAdmin(user, model.getDashboardId()))
            deleteById(model.getID());
        return model;
    }

    @Override
    public List<UserNameModel> getMembersByDashboard(Long id) {
        List<DashboardMember> dashboardMembers = memberRepository.findByDashboard(id).orElse(null);
        if (dashboardMembers == null)
            throw new ApiException("На доске нет участников", HttpStatus.NO_CONTENT);
        return dashboardMembers.stream()
                .map(x -> userNameConverter.convertToModel(x.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isAdmin(User user, Long id) {
        DashboardMember dashboardMember = memberRepository.findByDashboardIdAndUserId(id, user.getId()).orElse(null);
        if (dashboardMember == null)
            throw new ApiException("На доске нет такого участника", HttpStatus.NO_CONTENT);
        return dashboardMember.getIsAdmin();
    }
}
