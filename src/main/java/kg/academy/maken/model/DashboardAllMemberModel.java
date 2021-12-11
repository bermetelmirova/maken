package kg.academy.maken.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardAllMemberModel {
    private Long id;
    private List<DashboardMemberModel>  members;
}
