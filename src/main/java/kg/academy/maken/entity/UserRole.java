package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRole extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
