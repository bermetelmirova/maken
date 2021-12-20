package kg.academy.maken.model.dashboard_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardAddMemberModel {
    private Long idDashboard;
    private String loginUser;
}
