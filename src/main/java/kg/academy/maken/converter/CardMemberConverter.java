package kg.academy.maken.converter;

import kg.academy.maken.entity.CardMember;
import kg.academy.maken.model.card_model.CardMemberModel;
import org.springframework.stereotype.Component;

@Component
public class CardMemberConverter implements BaseConverter<CardMemberModel, CardMember> {
    @Override
    public CardMember convertToEntity(CardMemberModel cardMemberModel) {
        return null;
    }

    @Override
    public CardMemberModel convertToModel(CardMember cardMember) {
        return CardMemberModel.builder()
                .cardId(cardMember.getCard().getId())
                .dashboardMemberId(cardMember.getDashboardMember().getId())
                .build();
    }
}
