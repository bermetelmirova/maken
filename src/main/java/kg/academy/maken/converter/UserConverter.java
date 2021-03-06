package kg.academy.maken.converter;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.user_model.UserModel;

import org.springframework.stereotype.Component;

@Component
public class UserConverter implements BaseConverter<UserModel, User> {
    @Override
    public UserModel convertToModel(User user) {
        if (user == null) return null;
        return UserModel.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .image(user.getImage()!= null ? user.getImage().getImageUrl(): null)
                .build();
    }

    @Override
    public User convertToEntity(UserModel userModel) {
        if (userModel == null) return null;
        return User.builder()
                .login(userModel.getLogin())
                .email(userModel.getEmail())
                .password(userModel.getPassword())

                .build();
    }
}
