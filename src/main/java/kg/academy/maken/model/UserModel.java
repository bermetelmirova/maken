package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {
    private Long ID;
    private String login;
    private String telegram;
    private String password;
    private Long image;
}
