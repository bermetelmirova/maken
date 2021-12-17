package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card_members")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CardMember extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private DashboardMember dashboardMember;
}
