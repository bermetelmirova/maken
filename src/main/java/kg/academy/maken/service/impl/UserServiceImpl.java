package kg.academy.maken.service.impl;

import kg.academy.maken.entity.Dashboard;
import kg.academy.maken.entity.User;
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
    public UserModel saveModel(UserModel userModel) {
        User user = convertToEntity(userModel);
        userRepository.save(user);
        return userModel;
    }

    @Override
    public UserModel deleteModelById(Long id) {
        User userForDelete = convertToEntity(getModelById(id));
        if (userForDelete != null)
            userRepository.deleteById(id);
        return getModelById(id);
    }

    @Override
    public UserModel getModelById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserModel userModel = new UserModel();
        if (user != null)
            userModel = convertToModel(user);
        return userModel;
    }

    @Override
    public List<UserModel> getAllModel() {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userModels.add(convertToModel(users.get(i)));
        }
        return userModels;
    }

    @Override
    public UserModel updateModel(UserModel userModel) {
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

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteById(Long id) {
        User user = findById(id);
        if (user != null)
            userRepository.deleteById(id);
        return user;
    }
}
