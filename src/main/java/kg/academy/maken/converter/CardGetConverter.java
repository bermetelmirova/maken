package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.CardGetModel;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardGetConverter extends BaseConverter<CardGetModel, Card>{
    public CardGetConverter() {
        super(CardGetConverter::convertToEntity, CardGetConverter::convertToModel);
    }

    private static CardGetModel convertToModel(Card card) {
        if (card == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return CardGetModel.builder()
                .ID(card.getId())
                .name(card.getName())
                .deadline(card.getDeadline().toString())
                .description(card.getDescription())
                .adminRating(card.getAdminRating())
                .listId(card.getList().getId())
                .labelId(card.getLabel().getId())
                .build();
    }

    private static Card convertToEntity(CardGetModel cardGetModel) {
        if (cardGetModel == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Card.builder()
                .name(cardGetModel.getName())
                .description(cardGetModel.getDescription())
                .adminRating(cardGetModel.getAdminRating())
                .deadline(LocalDateTime.parse(cardGetModel.getDeadline(), dateTimeFormatter))
                .build();
    }
}
