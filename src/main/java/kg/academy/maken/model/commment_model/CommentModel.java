package kg.academy.maken.model.commment_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentModel {
    private Long id;
    private Long cardId;
    private String comment;
}
