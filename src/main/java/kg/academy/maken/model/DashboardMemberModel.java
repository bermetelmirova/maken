package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DashboardMemberModel {
    private Long ID;
    private Long userId;
    private Long dashboardId;
}
