package kg.academy.maken.model.commment_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CommentGetModel {
    private Long id;
    private String comment;
}
