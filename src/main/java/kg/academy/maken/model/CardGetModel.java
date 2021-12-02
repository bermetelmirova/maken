package kg.academy.maken.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardGetModel {
    private Long ID;
    private String name;
    private String deadline;
    private String description;
    private Double adminRating;
    private Long listId;
    private Long labelId;
    private List<MemberModel> members;
    private List<CommentModel> comments;
}
