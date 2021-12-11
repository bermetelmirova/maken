package kg.academy.maken.model;

import kg.academy.maken.entity.DashboardMember;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardMemberModel {
    private Long ID;
    private Long cardId;
    private DashboardMember dashboardMemberId;
}
