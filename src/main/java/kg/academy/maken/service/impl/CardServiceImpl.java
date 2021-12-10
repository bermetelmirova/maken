package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Card;
import kg.academy.maken.repository.CardRepository;
import kg.academy.maken.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

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
}
