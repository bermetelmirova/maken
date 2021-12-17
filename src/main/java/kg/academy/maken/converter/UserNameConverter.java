package kg.academy.maken.converter;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.user_model.UserNameModel;
import org.springframework.stereotype.Component;

@Component
public class UserNameConverter implements BaseConverter<UserNameModel, User> {
    @Override
    public User convertToEntity(UserNameModel userNameModel) {
        return User.builder()
                .login(userNameModel.getLogin())
                .build();
    }

    @Override
    public UserNameModel convertToModel(User user) {
        return UserNameModel.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }
}
