package kg.academy.maken.model.card_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardMemberModel {
    private Long cardId;
    private Long dashboardMemberId;
}
