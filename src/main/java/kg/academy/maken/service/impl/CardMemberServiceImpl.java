package kg.academy.maken.service.impl;

import kg.academy.maken.converter.CardMemberConverter;
import kg.academy.maken.entity.CardMember;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.card_model.CardMemberModel;
import kg.academy.maken.repository.CardMemberRepository;
import kg.academy.maken.service.CardMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardMemberServiceImpl implements CardMemberService {
    @Autowired
    private  CardMemberRepository cardMemberRepository;

    @Autowired
    public CardMemberConverter cardMemberConverter;

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

    @Override
    public List<CardMemberModel> getMembersByCard(Long id) {
        List<CardMember> dashboardMembers = cardMemberRepository.findByCardId(id)
                .orElseThrow(()-> new ApiException("Список участников пуст!", HttpStatus.NO_CONTENT));
        return dashboardMembers.stream()
                .map(cardMemberConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CardMember findByCardAndMember(Long cardId, Long userID) {
        return cardMemberRepository.findByCardAndDashboardMember(cardId, userID).orElse(null);
    }
}
