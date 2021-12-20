package kg.academy.maken.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Rating extends BaseEntity {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(name = "value", nullable = false)
    private Double value;
}
