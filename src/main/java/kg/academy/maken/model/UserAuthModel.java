package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserAuthModel {
    private String login;
    private String password;
}
