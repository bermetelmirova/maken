package kg.academy.maken.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User extends BaseEntity {
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private Long isActive;
}
