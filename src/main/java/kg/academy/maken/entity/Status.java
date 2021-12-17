package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Status extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
}
