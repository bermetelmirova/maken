package kg.academy.maken.model.list_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListModel {
    private Long id;
    private String name;
    private Long dashboardId;
    private Long statusId;
}
