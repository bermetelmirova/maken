package kg.academy.maken.model.card_model;

import kg.academy.maken.model.commment_model.CommentGetModel;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardGetModel {
    private Long id;
    private String name;
    private String deadline;
    private String description;
    private Double adminRating;
    private Long listId;
    private Long labelId;
    private List<CardMemberModel> members;
    private List<CommentGetModel> comments;
}
