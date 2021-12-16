package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.CardModel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CardModelConverter implements BaseConverter<CardModel, Card> {
    @Override
    public CardModel convertToModel(Card card) {
        if (card == null) return null;
        return CardModel.builder()
                .id(card.getId())
                .name(card.getName())
                .build();
    }

    @Override
    public Card convertToEntity(CardModel cardGetModel) {
        if (cardGetModel == null) return null;
        return Card.builder()
                .name(cardGetModel.getName())
                .build();
    }
}
