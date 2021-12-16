package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListStatusUpdateModel {
    private Long id;
    private Long statusId;
}
