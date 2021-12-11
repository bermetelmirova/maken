package kg.academy.maken.service.impl;

import kg.academy.maken.entity.DashboardMember;
import kg.academy.maken.model.DashboardMemberModel;
import kg.academy.maken.repository.MemberRepository;
import kg.academy.maken.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public DashboardMember deleteById(Long id) {
        DashboardMember dashboardMember = findById(id);
        if (dashboardMember != null)
            memberRepository.deleteById(id);
        return dashboardMember;
    }

    @Override
    public DashboardMemberModel addAdmin(DashboardMemberModel model) {
        DashboardMember dashboardMember = findById(model.getID());
        if(dashboardMember !=null) dashboardMember.setIsAdmin(true);
        save(dashboardMember);
        return model;
    }

    @Override
    public DashboardMemberModel removeAdmin(DashboardMemberModel model) {
        DashboardMember dashboardMember =findById(model.getID());
        if(dashboardMember !=null) dashboardMember.setIsAdmin(false);
        save(dashboardMember);
        return model;
    }
}
