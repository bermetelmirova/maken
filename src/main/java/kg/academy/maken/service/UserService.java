package kg.academy.maken.service;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends BaseService<User> {
    UserModel saveModel(UserModel userModel);

    UserModel deleteModelById(Long id);

    UserModel getModelById(Long id);

    List<UserModel> getAllModel();

    UserModel updateModel(UserModel userModel);

    UserNameUpdate updateModel(UserNameUpdate userUpdateModel);

    UserUpdatePasswordModel updateModel(UserUpdatePasswordModel userUpdatePasswordModel);

    User getByLogin(String login);

    String getAuthorisationToken(UserAuthModel userModel);

    Page<User> searchUser(UserSearch userSearch, Pageable pageable);
}
