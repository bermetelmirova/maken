package kg.academy.maken.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListGetModel {
    private Long id;
    private List<CardModel> cardModels;
}
