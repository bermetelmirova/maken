package kg.academy.maken.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Comment extends BaseEntity{
    @Column(name = "text", nullable = false)
    private String text;
}
