package kg.academy.maken.service;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.user_model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends BaseService<User> {
    UserTokenModel saveModel(UserModel userModel);

    UserModel deleteModelById(Long id);

    UserModel getModelById(Long id);

    List<UserModel> getAllModel();

    UserModel updateModel(UserModel userModel);

    UserNameUpdate updateModel(UserNameUpdate userUpdateModel);

    UserUpdatePasswordModel updateModel(UserUpdatePasswordModel userUpdatePasswordModel);

    User getByLogin(String login);

    UserTokenModel getAuthorisationToken(UserAuthModel userModel);

    Page<User> searchUser(UserSearch userSearch, Pageable pageable);

    User getCurrentUser();

    UserModel setImage(MultipartFile multipartFile);

}
