package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.card_model.CardGetModel;
import kg.academy.maken.service.ListService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class CardGetConverter implements BaseConverter<CardGetModel, Card> {
    private final ListService listService;

    @Override
    public Card convertToEntity(CardGetModel cardGetModel) {
        if (cardGetModel == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Card.builder()
                .name(cardGetModel.getName())
                .description(cardGetModel.getDescription())
                .list(listService.findById(cardGetModel.getListId()))
                .adminRating(cardGetModel.getAdminRating())
                .deadline(LocalDateTime.parse(cardGetModel.getDeadline(), dateTimeFormatter))
                .build();
    }

    @Override
    public CardGetModel convertToModel(Card card) {
        if (card == null) return null;
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
}
