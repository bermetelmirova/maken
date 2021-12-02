package kg.academy.maken.service.impl;

import kg.academy.maken.entity.User;
import kg.academy.maken.entity.UserRole;
import kg.academy.maken.model.UserModel;
import kg.academy.maken.repository.UserRepository;
import kg.academy.maken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel save(UserModel userModel) {
        User user = convertToEntity(userModel);
        userRepository.save(user);
        return userModel;
    }

    @Override
    public UserModel deleteById(Long id) {
        User userForDelete = convertToEntity(getById(id));
        if (userForDelete != null)
            userRepository.deleteById(id);
        return null;
    }

    @Override
    public UserModel getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserModel userModel = new UserModel();
        if (user != null)
            userModel = convertToModel(user);
        return userModel;
    }

    @Override
    public List<UserModel> getAll() {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userModels.add(convertToModel(users.get(i)));
        }
        return userModels;
    }

    @Override
    public UserModel update(UserModel userModel) {
        return null;
    }

    private User convertToEntity(UserModel userModel) {
        return User.builder()
                .login(userModel.getLogin())
                .telegram(userModel.getTelegram())
                .password(userModel.getPassword())
                .build();
    }

    private UserModel convertToModel(User user) {
        return UserModel.builder()
                .login(user.getLogin())
                .telegram(user.getTelegram())
                .password(user.getPassword())
                .image(user.getImage().getId())
                .build();
    }
}
