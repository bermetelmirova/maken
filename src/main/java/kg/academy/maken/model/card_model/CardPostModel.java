package kg.academy.maken.model.card_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardPostModel {
    private Long id;
    private String name;
    private String deadline;
    private String description;
    private Double adminRating;
    private Long listId;
    private Long labelId;
}
