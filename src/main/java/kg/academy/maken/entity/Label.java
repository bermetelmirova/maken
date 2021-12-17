package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "label")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Label extends BaseEntity {
    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "name", nullable = false)
    private String name;
}
