package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.card_model.CardPostModel;
import kg.academy.maken.repository.ListRepository;
import kg.academy.maken.service.LabelService;

import kg.academy.maken.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CardPostConverter implements BaseConverter<CardPostModel, Card> {
    @Autowired
    private ListService listService;
    @Autowired
    private LabelService labelService;

    @Override
    public CardPostModel convertToModel(Card card) {
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

    @Override
    public Card convertToEntity(CardPostModel cardPostModel) {
        if (cardPostModel == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return Card.builder()
                .name(cardPostModel.getName())
                .description(cardPostModel.getDescription())
                .adminRating(cardPostModel.getAdminRating())
                .deadline(LocalDateTime.parse(cardPostModel.getDeadline(), dateTimeFormatter))
                .list(listService.findById(cardPostModel.getListId()))
                .label(labelService.findById(cardPostModel.getLabelId()))
                .build();
    }
}
