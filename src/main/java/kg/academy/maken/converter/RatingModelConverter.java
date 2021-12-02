package kg.academy.maken.converter;

import kg.academy.maken.entity.Rating;
import kg.academy.maken.model.RatingModel;

public class RatingModelConverter extends BaseConverter<RatingModel, Rating>{
    public RatingModelConverter() {
        super(RatingModelConverter::convertToEntity, RatingModelConverter::convertToModel);
    }

    private static RatingModel convertToModel(Rating rating) {
        if (rating == null) return null;
        return RatingModel.builder()
                .ID(rating.getId())
                .cardId(rating.getCard().getId())
                .value(rating.getValue())
                .build();
    }

    private static Rating convertToEntity(RatingModel ratingModel) {
        if (ratingModel == null) return null;
        return Rating.builder()
                .value(ratingModel.getValue())
                .build();
    }
}
