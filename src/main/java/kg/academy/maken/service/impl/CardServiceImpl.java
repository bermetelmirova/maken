package kg.academy.maken.service.impl;

import kg.academy.maken.converter.CardChangeListConverter;
import kg.academy.maken.converter.CardGetConverter;
import kg.academy.maken.converter.CardModelConverter;
import kg.academy.maken.converter.CardPostConverter;
import kg.academy.maken.entity.*;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.card_model.*;
import kg.academy.maken.model.commment_model.CommentGetModel;
import kg.academy.maken.model.commment_model.CommentModel;
import kg.academy.maken.repository.CardRepository;
import kg.academy.maken.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CardGetConverter cardGetConverter;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ListService listService;
    @Autowired
    private CardChangeListConverter modelListConverter;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CardMemberService cardMemberService;
    @Autowired
    private DashboardMemberService memberService;
    @Autowired
    private UserService userService;

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
        return cardRepository.findById(id)
                .orElseThrow(() -> new ApiException("Карта не найдена!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Card deleteById(Long id) {
        Card card = findById(id);
        if (card == null)
            throw new ApiException("Карта не найдена!", HttpStatus.BAD_REQUEST);
        else
            cardRepository.deleteById(id);
        return card;
    }

    @Override
    public List<CardModel> getCardsByList(Long id) {
        List<Card> card = cardRepository.findByListId(id).orElse(null);
        if (card == null) {
            throw new ApiException("В листе нет карточек!", HttpStatus.NO_CONTENT);
        }
        return card.stream()
                .map(cardModelConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public CardChangeListModel changeList(CardChangeListModel cardChangeListModel) {
        Card card = findById(cardChangeListModel.getCardId());
        kg.academy.maken.entity.List list = listService.findById(cardChangeListModel.getListId());
        if (list == null || card == null)
            throw new ApiException("Указаны не существующие данные", HttpStatus.BAD_REQUEST);
        else {
            card.setList(list);
            cardRepository.save(card);
        }
        return modelListConverter.convertToModel(card);
    }

    @Override
    public CommentModel addComment(CommentModel commentModel) {
        if (commentModel.getComment() == null)
            throw new ApiException("Не написан текст!", HttpStatus.BAD_REQUEST);
        Card card = findById(commentModel.getCardId());
        User user = userService.findById(commentModel.getUserId());
        Comment comment = new Comment();
        comment.setText(commentModel.getComment());
        comment.setCard(card);
        comment.setUser(user);
        commentService.save(comment);
        return commentModel;
    }

    @Override
    public CardMemberModel addMember(CardMemberModel cardMemberModel) {
        DashboardMember dashboardMember = memberService.findById(cardMemberModel.getDashboardMemberId());
        Card card = findById(cardMemberModel.getCardId());
        CardMember cardMember = new CardMember();
        if (card == null)
            throw new ApiException("Карточка не найдена", HttpStatus.BAD_REQUEST);
        cardMember.setDashboardMember(dashboardMember);
        cardMember.setCard(card);
        cardMemberService.save(cardMember);
        return cardMemberModel;
    }

    @Override
    public CardMemberModel removeMember(CardMemberModel cardMemberModel) {
        CardMember cardMember = cardMemberService
                .findByCardAndMember(cardMemberModel.getCardId(), cardMemberModel.getDashboardMemberId());
        if (cardMember != null)
            cardMemberService.deleteById(cardMember.getId());
        return cardMemberModel;
    }

    @Override
    public CardPostModel rejectTask(CardPostModel cardModel) {
        Card card = findById(cardModel.getID());
        Long idDashboard = listService.findById(cardModel.getListId()).getDashboard().getId();
        kg.academy.maken.entity.List list = listService.findByStatusOnDashboard(2L, idDashboard);
        card.setList(list);
        cardRepository.save(card);
        return cardPostConverter.convertToModel(card);
    }

    @Override
    public CardRatingModel acceptTask(CardRatingModel cardModel) {
        Card card = findById(cardModel.getCardId());
        if (cardModel.getAdminRating() < 0)
            throw new ApiException("Оценка меньше нуля", HttpStatus.BAD_REQUEST);
        else {
            card.setAdminRating(cardModel.getAdminRating());
            save(card);
        }
        return cardModel;
    }

    @Override
    public CardGetModel getCard(Long id) {
        Card card = findById(id);
        if (card == null)
            throw new ApiException("Нет такой карточки", HttpStatus.NO_CONTENT);
        CardGetModel cardGetModel = cardGetConverter.convertToModel(card);
        List<CommentGetModel> comments = commentService.getByCardId(card.getId());
        List<CardMemberModel> memberModels = cardMemberService.getMembersByCard(card.getId());
        cardGetModel.setComments(comments);
        cardGetModel.setMembers(memberModels);
        return cardGetModel;
    }

    @Override
    public CardPostModel saveModel(CardPostModel model) {
        Card card = cardPostConverter.convertToEntity(model);
        cardRepository.save(card);
        return cardPostConverter.convertToModel(card);
    }

    @Override
    public CardPostModel deleteModelById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getByLogin(userName);
        if (!memberService.isAdmin(user, cardRepository.findDashboardByCardId(id)))
            throw new ApiException("Пользователь не является админом!", HttpStatus.FORBIDDEN);
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
    public Page<CardModel> getPage(Pageable pageable) {
        Page<Card> cards = cardRepository.findAll(pageable);
        return cards.map(cardModelConverter::convertToModel);
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
        if (model.getLabelId() != null)
            card.setLabel(labelService.findById(model.getLabelId()));
        cardRepository.save(card);
        return cardPostConverter.convertToModel(card);
    }
}
