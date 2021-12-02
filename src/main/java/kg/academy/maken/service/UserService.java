package kg.academy.maken.service;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.UserModel;

import java.util.List;

public interface UserService extends BaseService<User>{
    UserModel saveModel(UserModel userModel);
    UserModel deleteModelById(Long id);
    UserModel getModelById(Long id);
    List <UserModel> getAllModel();
    UserModel updateModel(UserModel userModel);
}
