package kg.academy.maken.converter;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<UserModel, User> {
    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToModel);
    }

    private static UserModel convertToModel(User user) {
        if (user == null) return null;
        return UserModel.builder()
                .ID(user.getId())
                .login(user.getLogin())
                .telegram(user.getTelegram())
                .password(user.getPassword())
//                .image(user.getImage().getId())
                .build();
    }

    private static User convertToEntity(UserModel userModel) {
        if (userModel == null) return null;
        return User.builder()
                .login(userModel.getLogin())
                .telegram(userModel.getTelegram())
                .password(userModel.getPassword())
                .build();
    }
}
