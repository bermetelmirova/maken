package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.CardPostModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CardPostConverter extends BaseConverter<CardPostModel, Card>{
    public CardPostConverter() {
        super(CardPostConverter::convertToEntity, CardPostConverter::convertToModel);
    }

    private static CardPostModel convertToModel(Card card) {
        if (card == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return CardPostModel.builder()
                .ID(card.getId())
                .name(card.getName())
                .deadline(card.getDeadline().toString())
                .description(card.getDescription())
                .adminRating(card.getAdminRating())
                .listId(card.getList().getId())
                .labelId(card.getLabel().getId())
                .build();
    }

    private static Card convertToEntity(CardPostModel cardPostModel) {
        if (cardPostModel == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Card.builder()
                .name(cardPostModel.getName())
                .description(cardPostModel.getDescription())
                .adminRating(cardPostModel.getAdminRating())
                .deadline(LocalDateTime.parse(cardPostModel.getDeadline(), dateTimeFormatter))
                .build();
    }
}
