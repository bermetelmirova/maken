package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dashboards")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Dashboard extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image background;
}
