package kg.academy.maken.service;

import kg.academy.maken.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel save(UserModel userModel);
    UserModel deleteById(Long id);
    UserModel getById(Long id);
    List <UserModel> getAll();
    UserModel update(UserModel userModel);
}
