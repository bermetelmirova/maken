package kg.academy.maken.model.user_model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserNameUpdate {
    private String token;
    private String login;
}
