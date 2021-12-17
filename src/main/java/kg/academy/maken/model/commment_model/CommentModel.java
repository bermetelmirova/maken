package kg.academy.maken.model.commment_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentModel {
    private Long ID;
    private Long cardId;
    private Long userId;
    private String comment;
}
