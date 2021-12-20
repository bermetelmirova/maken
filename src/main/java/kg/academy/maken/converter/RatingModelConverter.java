package kg.academy.maken.converter;

import kg.academy.maken.entity.Rating;
import kg.academy.maken.model.rating_model.RatingModel;
import kg.academy.maken.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RatingModelConverter implements BaseConverter<RatingModel, Rating> {
    private final CardService cardService;

    @Override
    public RatingModel convertToModel(Rating rating) {
        if (rating == null) return null;
        return RatingModel.builder()
                .id(rating.getId())
                .cardId(rating.getCard().getId())
                .value(rating.getValue())
                .build();
    }

    public Rating convertToEntity(RatingModel ratingModel) {
        if (ratingModel == null) return null;
        return Rating.builder()
                .card(cardService.findById(ratingModel.getCardId()))
                .value(ratingModel.getValue())
                .build();
    }
}
