package kg.academy.maken.model.user_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {
    private Long id;
    private String login;
    private String email;
    private String password;
    private Long image;
}
