package kg.academy.maken.model.commment_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentGetModel {
    private Long ID;
    private Long userId;
    private String comment;
}
