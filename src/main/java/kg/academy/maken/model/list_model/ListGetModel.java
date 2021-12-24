package kg.academy.maken.model.list_model;

import kg.academy.maken.model.card_model.CardModel;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListGetModel {
    private Long id;
    private String name;
    private List<CardModel> cardModels;
}
