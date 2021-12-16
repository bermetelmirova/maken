package kg.academy.maken.service.impl;

import kg.academy.maken.converter.CardChangeListModelConverter;
import kg.academy.maken.converter.CardModelConverter;
import kg.academy.maken.converter.CardPostConverter;
import kg.academy.maken.entity.Card;
import kg.academy.maken.entity.CardComment;
import kg.academy.maken.entity.Comment;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.CardChangeListModel;
import kg.academy.maken.model.CardCommentModel;
import kg.academy.maken.model.CardModel;
import kg.academy.maken.model.CardPostModel;
import kg.academy.maken.repository.CardRepository;
import kg.academy.maken.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardModelConverter cardModelConverter;
    @Autowired
    private CardPostConverter cardPostConverter;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ListService listService;
    @Autowired
    private CardChangeListModelConverter modelListConverter;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CardCommentService cardCommentService;

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card deleteById(Long id) {
        Card card = findById(id);
        if (card != null)
            cardRepository.deleteById(id);
        return card;
    }

    @Override
    public List<CardModel> getCardsByList(Long id) {
        List<Card> card = cardRepository.findByList(id).orElse(null);
        if (card == null) {
            throw new ApiException("Cписок пуст", HttpStatus.NO_CONTENT);
        }
        return card.stream()
                .map(cardModelConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CardChangeListModel changeList(CardChangeListModel cardChangeListModel) {
        Card card = findById(cardChangeListModel.getListId());
        kg.academy.maken.entity.List list = listService.findById(cardChangeListModel.getListId());
        if(list== null || card == null)
            throw new ApiException("Указаны не существующие данные", HttpStatus.BAD_REQUEST);
        else {
            card.setList(list);
            cardRepository.save(card);
        }
        return modelListConverter.convertToModel(card);
    }

    @Override
    public CardCommentModel addComment(CardCommentModel cardCommentModel) {
        Comment comment = commentService.save(new Comment(cardCommentModel.getComment()));
        Card card = findById(cardCommentModel.getCardId());
        CardComment cardComment = new CardComment();
        cardComment.setComment(comment);
        cardComment.setComment(comment);
        cardCommentService.save(cardComment);
        return null;
    }

    @Override
    public CardPostModel saveModel(CardPostModel model) {
        Card card = cardPostConverter.convertToEntity(model);
        cardRepository.save(card);
        return null;
    }

    @Override
    public CardPostModel deleteModelById(Long id) {
        return cardPostConverter.convertToModel(deleteById(id));
    }

    @Override
    public CardPostModel getModelById(Long id) {
        return cardPostConverter.convertToModel(findById(id));
    }

    @Override
    public List<CardPostModel> getAllModel() {
        return cardRepository.findAll()
                .stream().map(cardPostConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CardPostModel> getPage(Pageable pageable) {
        Page<Card> cards = cardRepository.findAll(pageable);
        return cards.map(cardPostConverter::convertToModel);
    }

    @Override
    public CardPostModel update(CardPostModel model) {
        Card card = findById(model.getID());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (model.getAdminRating() != null)
            card.setAdminRating(model.getAdminRating());
        if (model.getDeadline() != null)
            card.setDeadline(LocalDateTime.parse(model.getDeadline(), dateTimeFormatter));
        if (model.getName() != null)
            card.setName(model.getName());
        if (model.getDescription() != null)
            card.setDescription(model.getDescription());
        if(model.getLabelId()!=null)
            card.setLabel(labelService.findById(model.getLabelId()));
        cardRepository.save(card);
        return cardPostConverter.convertToModel(card);
    }
}
