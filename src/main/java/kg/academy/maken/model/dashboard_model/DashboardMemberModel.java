package kg.academy.maken.model.dashboard_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DashboardMemberModel {
    private Long id;
    private Long userId;
    private Long dashboardId;
}
