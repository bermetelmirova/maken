package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.card_model.CardRatingModel;
import org.springframework.stereotype.Component;

@Component
public class CardRatingConverter implements BaseConverter<CardRatingModel, Card>{

    @Override
    public Card convertToEntity(CardRatingModel model) {
        return null;
    }

    @Override
    public CardRatingModel convertToModel(Card card) {
        return CardRatingModel.builder()
                .id(card.getId())
                .name(card.getName())
                .adminRating(card.getAdminRating())
                .build();
    }
}
