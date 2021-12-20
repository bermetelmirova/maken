package kg.academy.maken.model.user_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserTokenModel {
    private String token;
    private Long id;
    private String login;
    private String telegram;
    private String password;
    private Long image;
}
