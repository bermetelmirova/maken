package kg.academy.maken.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card_comments")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardComment extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
