package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DashboardMember extends BaseEntity{
    @ManyToOne
    @JoinColumn(name  = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "dashboard_id", nullable = false)
    private Dashboard dashboard;

    @Column(name  = "is_admin", nullable = false)
    private Boolean isAdmin;
}
