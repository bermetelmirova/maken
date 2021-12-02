package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LabelModel {
    private Long ID;
    private String color;
    private String name;
}
