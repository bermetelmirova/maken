package kg.academy.maken.converter;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.card_model.CardChangeListModel;
import kg.academy.maken.service.ListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardChangeListConverter implements BaseConverter<CardChangeListModel, Card> {
    @Autowired
    private ListService listService;

    @Override
    public Card convertToEntity(CardChangeListModel cardChangeListModel) {
        return Card.builder()
                .list(listService.findById(cardChangeListModel.getListId()))
                .build();
    }

    @Override
    public CardChangeListModel convertToModel(Card card) {
        return CardChangeListModel.builder()
                .cardId(card.getId())
                .listId(card.getList().getId())
                .build();
    }
}
