package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Rating extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(name = "value", nullable = false)
    private Double value;
}
