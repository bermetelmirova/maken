package kg.academy.maken.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListModel {
    private Long ID;
    private String name;
    private Long DashboardId;
}
