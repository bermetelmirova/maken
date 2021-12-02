package kg.academy.maken.service.impl;

import kg.academy.maken.entity.CardComment;
import kg.academy.maken.repository.CardCommentRepository;
import kg.academy.maken.service.CardCommentService;

import java.util.List;

public class CardCommentServiceImpl implements CardCommentService {
   private final CardCommentRepository cardCommentRepository;

    public CardCommentServiceImpl(CardCommentRepository cardCommentRepository) {
        this.cardCommentRepository = cardCommentRepository;
    }

    @Override
    public CardComment save(CardComment cardComment) {
        return cardCommentRepository.save(cardComment);
    }

    @Override
    public List<CardComment> getAll() {
        return cardCommentRepository.findAll();
    }

    @Override
    public CardComment findById(Long id) {
        return cardCommentRepository.findById(id).orElse(null);
    }

    @Override
    public CardComment deleteById(Long id) {
        CardComment cardComment = findById(id);
        if (cardComment != null)
            cardCommentRepository.deleteById(id);
        return cardComment;
    }
}
