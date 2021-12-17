package kg.academy.maken.model.rating_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RatingModel {
    private Long ID;
    private Long cardId;
    private Double value;
}
