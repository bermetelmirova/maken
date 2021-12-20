package kg.academy.maken.model.label_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LabelModel {
    private Long id;
    private String color;
    private String name;
}
