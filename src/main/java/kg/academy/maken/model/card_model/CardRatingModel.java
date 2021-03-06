package kg.academy.maken.model.card_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardRatingModel {
    private Long id;
    private String name;
    private Double adminRating;
}
