package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Member;
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
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member deleteById(Long id) {
        Member member = findById(id);
        if (member != null)
            memberRepository.deleteById(id);
        return member;
    }
}
