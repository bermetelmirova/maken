package kg.academy.maken.service;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.CardChangeListModel;
import kg.academy.maken.model.CardCommentModel;
import kg.academy.maken.model.CardModel;
import kg.academy.maken.model.CardPostModel;

import java.util.List;

public interface CardService extends BaseService<Card>, BaseModelService<CardPostModel> {
    List<CardModel> getCardsByList(Long id);
    CardChangeListModel changeList(CardChangeListModel cardChangeListModel);
    CardCommentModel addComment(CardCommentModel cardCommentModel);
}
