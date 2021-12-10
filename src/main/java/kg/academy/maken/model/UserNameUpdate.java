package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserNameUpdate {
    private Long id;
    private String login;
}
