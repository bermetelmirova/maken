package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Role extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
}
