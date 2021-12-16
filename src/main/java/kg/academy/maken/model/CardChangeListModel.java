package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardChangeListModel {
    private Long listId;
    private Long cardId;
}
