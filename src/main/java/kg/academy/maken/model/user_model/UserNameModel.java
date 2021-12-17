package kg.academy.maken.model.user_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNameModel {
    private Long id;
    private String login;
    private Boolean isAdmin;
}
