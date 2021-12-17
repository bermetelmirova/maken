package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lists")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class List extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "dashboard_id", nullable = false)
    private Dashboard dashboard;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
