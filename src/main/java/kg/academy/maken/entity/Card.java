package kg.academy.maken.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Card extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "admin_rating")
    private Double adminRating;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "list_id", nullable = false)
    private List list;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "label_id")
    private Label label;
}
