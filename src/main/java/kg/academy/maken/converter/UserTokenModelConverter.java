package kg.academy.maken.converter;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.user_model.UserTokenModel;
import org.springframework.stereotype.Component;

@Component
public class UserTokenModelConverter implements BaseConverter<UserTokenModel, User>{
    @Override
    public User convertToEntity(UserTokenModel userTokenModel) {
        return User.builder()
                .login(userTokenModel.getLogin())
                .email(userTokenModel.getEmail())
                .build();
    }

    @Override
    public UserTokenModel convertToModel(User user) {
        return UserTokenModel.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
