package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardCommentModel {
    private Long ID;
    private Long cardId;
    private Long commentId;
}
