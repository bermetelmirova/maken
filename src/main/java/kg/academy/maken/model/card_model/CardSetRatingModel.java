package kg.academy.maken.model.card_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardSetRatingModel {
    private Long cardId;
    private Double adminRating;
}
