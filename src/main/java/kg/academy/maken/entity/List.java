package kg.academy.maken.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "lists")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class List extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dashboard_id", nullable = false)
    private Dashboard dashboard;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
