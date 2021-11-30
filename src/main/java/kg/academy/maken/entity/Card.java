package kg.academy.maken.entity;

import lombok.*;

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
    private String adminRating;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private List list;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;
}
