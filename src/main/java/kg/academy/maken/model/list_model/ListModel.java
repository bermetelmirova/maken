package kg.academy.maken.model.list_model;

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
    private Long dashboardId;
    private Long statusId;
}
