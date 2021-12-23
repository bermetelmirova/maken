package kg.academy.maken.service.impl;

import kg.academy.maken.aop.Mail;
import kg.academy.maken.converter.UserConverter;
import kg.academy.maken.converter.UserTokenModelConverter;
import kg.academy.maken.entity.Image;
import kg.academy.maken.entity.User;
import kg.academy.maken.entity.UserRole;
import kg.academy.maken.exception.ApiException;
import kg.academy.maken.model.user_model.*;
import kg.academy.maken.repository.UserRepository;
import kg.academy.maken.service.*;
import kg.academy.maken.specification.UserSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserTokenModelConverter userTokenModelConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ImageService imageService;


    @Override
    @Mail(message = "user")
    public UserTokenModel saveModel(UserModel userModel) {
        User checkLogin = userRepository.findByLogin(userModel.getLogin()).orElse(null);
        if (checkLogin != null)
            throw new ApiException("Такой пользователь уже есть!", HttpStatus.BAD_REQUEST);
        User user = userConverter.convertToEntity(userModel);
        user.setIsActive(1L);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);


        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleService.findById(1L));
        userRoleService.save(userRole);


        String loginPasswordPair = userModel.getLogin() + ":" + userModel.getPassword();
        String token = "Basic " + new String(Base64.getEncoder().encode(loginPasswordPair.getBytes()));
        UserTokenModel userTokenModel = userTokenModelConverter.convertToModel(user);
        userTokenModel.setToken(token);
        return userTokenModel;
    }

    @Override
    public UserModel deleteModelById(Long id) {
        User userForDelete = userConverter.convertToEntity(getModelById(id));
        if (userForDelete != null)
            userRepository.deleteById(id);
        return userConverter.convertToModel(userForDelete);
    }

    @Override
    public UserModel getModelById(Long id) {
        User user = findById(id);
        UserModel userModel = new UserModel();
        if (user != null)
            userModel = userConverter.convertToModel(user);
        return userModel;
    }

    @Override
    public List<UserModel> getAllModel() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel updateModel(UserModel userModel) {
        User user = getCurrentUser();
        if (userModel.getLogin() != null)
            user.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        if (userModel.getEmail() != null)
            user.setEmail(userModel.getEmail());
        userRepository.save(user);
        return userConverter.convertToModel(user);
    }

    @Override
    public UserNameUpdate updateModel(UserNameUpdate userUpdateModel) {
        User user = getCurrentUser();
        if (userUpdateModel.getLogin() != null) {
            User checkLogin = userRepository.findByLogin(userUpdateModel.getLogin()).orElse(null);
            if (checkLogin != null)
                throw new ApiException("Такой пользователь уже есть!", HttpStatus.BAD_REQUEST);
            user.setLogin(userUpdateModel.getLogin());
        }
        userRepository.save(user);
        return userUpdateModel;
    }

    @Override
    public UserUpdatePasswordModel updateModel(UserUpdatePasswordModel userUpdatePasswordModel) {
        User user = getCurrentUser();
        if (userUpdatePasswordModel != null)
            user.setPassword(passwordEncoder.encode(userUpdatePasswordModel.getPassword()));
        userRepository.save(user);
        return userUpdatePasswordModel;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException("Пользователь не найден", HttpStatus.BAD_REQUEST));
    }

    @Override
    public User deleteById(Long id) {
        User user = findById(id);
        if (user == null)
            throw new ApiException("Пользователь не найден!", HttpStatus.BAD_REQUEST);
        userRepository.deleteById(id);
        return user;
    }

    @Override
    public UserTokenModel getAuthorisationToken(UserAuthModel userModel) {
        User user = userRepository.findByLogin(userModel.getLogin()).orElseThrow(() -> new IllegalArgumentException("Такого user'а нет!"));
        boolean isMatches = passwordEncoder.matches(userModel.getPassword(), user.getPassword());
        if (!isMatches) {
            throw new ApiException("Неверный пароль или логин!", HttpStatus.BAD_REQUEST);
        }
        String loginPasswordPair = userModel.getLogin() + ":" + userModel.getPassword();
        String token = "Basic " + new String(Base64.getEncoder().encode(loginPasswordPair.getBytes()));
        UserTokenModel userTokenModel = userTokenModelConverter.convertToModel(user);
        userTokenModel.setToken(token);
        return userTokenModel;
    }

    @Override
    public Page<User> searchUser(UserSearch userSearch, Pageable pageable) {
        UserSpecification userSpecification = new UserSpecification(userSearch);
        Page<User> userPage = userRepository.findAll(userSpecification, pageable);
        if (userPage.getContent().isEmpty())
            throw new ApiException("Список пустой", HttpStatus.BAD_REQUEST);
        return userPage;
    }

    @Override
    public User getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(userName);
    }

    @Override
    public UserModel setImage(MultipartFile multipartFile) {
        User user = getCurrentUser();
        Image image = imageService.saveImage(multipartFile);
        user.setImage(image);
        return userConverter.convertToModel(user);
    }
}
