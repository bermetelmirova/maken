package kg.academy.maken.service.impl;

import kg.academy.maken.entity.CardMember;
import kg.academy.maken.repository.CardMemberRepository;
import kg.academy.maken.service.CardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardMemberServiceImpl implements CardMemberService {
    private final CardMemberRepository cardMemberRepository;

    @Autowired
    public CardMemberServiceImpl(CardMemberRepository cardMemberRepository) {
        this.cardMemberRepository = cardMemberRepository;
    }

    @Override
    public CardMember save(CardMember cardMember) {
        return cardMemberRepository.save(cardMember);
    }

    @Override
    public List<CardMember> getAll() {
        return cardMemberRepository.findAll();
    }

    @Override
    public CardMember findById(Long id) {
        return cardMemberRepository.findById(id).orElse(null);
    }

    @Override
    public CardMember deleteById(Long id) {
        CardMember cardMember = findById(id);
        if (cardMember != null)
            cardMemberRepository.deleteById(id);
        return cardMember;
    }
}
