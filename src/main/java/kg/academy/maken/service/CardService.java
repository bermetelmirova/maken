package kg.academy.maken.service;

import kg.academy.maken.entity.Card;
import kg.academy.maken.model.card_model.*;
import kg.academy.maken.model.commment_model.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService extends BaseService<Card>, BaseModelService<CardPostModel> {
    List<CardModel> getCardsByList(Long id);

    CardChangeListModel changeList(CardChangeListModel cardChangeListModel);

    CommentModel addComment(CommentModel cardCommentModel);

    CardMemberModel addMember(CardMemberModel cardMemberModel);

    CardMemberModel removeMember(CardMemberModel cardMemberModel);

    CardPostModel rejectTask(CardPostModel cardModel);

    CardRatingModel acceptTask(CardRatingModel cardModel);

    CardGetModel getCard(Long id);

    Page<CardModel> getPage(Pageable pageable);

    List<Card> getDoneCards();

    Boolean sendMail(CommentModel commentModel);

    Boolean sendMail(CardMemberModel cardMemberModel);

    Boolean sendMail(CardPostModel cardPostModel);

    Boolean sendMail(CardRatingModel cardRatingModel);
}
