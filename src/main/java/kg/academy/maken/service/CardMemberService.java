package kg.academy.maken.service;

import kg.academy.maken.entity.CardMember;
import kg.academy.maken.model.card_model.CardMemberModel;

import java.util.List;

public interface CardMemberService extends BaseService<CardMember>{
    List<CardMemberModel> getMembersByCard(Long id);
    CardMember findByCardAndMember(Long cardId, Long userID);
}
