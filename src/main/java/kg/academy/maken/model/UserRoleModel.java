package kg.academy.maken.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRoleModel {
    private Long ID;
    private Long userId;
    private Long roleId;
}
