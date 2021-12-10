package kg.academy.maken.service.impl;

import kg.academy.maken.converter.UserConverter;
import kg.academy.maken.entity.User;
import kg.academy.maken.entity.UserRole;
import kg.academy.maken.model.UserAuthModel;
import kg.academy.maken.model.UserModel;
import kg.academy.maken.model.UserNameUpdate;
import kg.academy.maken.model.UserSearch;
import kg.academy.maken.repository.UserRepository;
import kg.academy.maken.service.RoleService;
import kg.academy.maken.service.UserRoleService;
import kg.academy.maken.service.UserService;
import kg.academy.maken.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder, RoleService roleService, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserModel saveModel(UserModel userModel) {
        User user = userConverter.convertFromModel(userModel);
        user.setIsActive(1L);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleService.findById(1L));
        userRoleService.save(userRole);

        return userConverter.convertFromEntity(user);
    }

    @Override
    public UserModel deleteModelById(Long id) {
        User userForDelete = userConverter.convertFromModel(getModelById(id));
        if (userForDelete != null)
            userRepository.deleteById(id);
        return getModelById(id);
    }

    @Override
    public UserModel getModelById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserModel userModel = new UserModel();
        if (user != null)
            userModel = userConverter.convertFromEntity(user);
        return userModel;
    }

    @Override
    public List<UserModel> getAllModel() {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            userModels.add(userConverter.convertFromEntity(users.get(i)));
        }
        return userModels;
    }

    @Override
    public UserModel updateModel(UserModel userModel) {
        User user = userRepository.getById(userModel.getID());
        if (userModel.getLogin() != null)
            user.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        if (userModel.getTelegram() != null)
            user.setTelegram(userModel.getTelegram());
        userRepository.save(user);
        return userConverter.convertFromEntity(user);
    }

    @Override
    public UserNameUpdate updateModel(UserNameUpdate userUpdateModel) {
        Long id = userUpdateModel.getId();
        User user = findById(id);
        if (userUpdateModel.getLogin() != null)
            user.setLogin(userUpdateModel.getLogin());
        userRepository.save(user);
        return userUpdateModel;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
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

    @Override
    public String getAuthorisationToken(UserAuthModel userModel) {
        User user = userRepository.findByLogin(userModel.getLogin()).orElseThrow(() -> new IllegalArgumentException("Такого user'а нет!"));
        boolean isMatches = passwordEncoder.matches(userModel.getPassword(), user.getPassword());
        if (!isMatches) {
            throw new IllegalArgumentException("Неверный пароль или логин");
        }
        String loginPasswordPair = userModel.getLogin() + ":" + userModel.getPassword();
        return "Basic " + new String(Base64.getEncoder().encode(loginPasswordPair.getBytes()));

    }

    @Override
    public Page<User> searchUser(UserSearch userSearch, Pageable pageable) {
        UserSpecification userSpecification = new UserSpecification(userSearch);
        Page<User> userPage = userRepository.findAll(userSpecification, pageable);
//        if (userPage.getContent().isEmpty())
//            throw new ApiException("Список пустой", HttpStatus.BAD_REQUEST);
        return userPage;
    }
}
