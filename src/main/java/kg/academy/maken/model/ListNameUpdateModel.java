package kg.academy.maken.model;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListNameUpdateModel {
    private Long ID;
    private String name;
}
