package kg.academy.maken.controller;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.ResponseMessage;
import kg.academy.maken.model.user_model.*;
import kg.academy.maken.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseMessage<UserTokenModel> save(@RequestBody UserModel userModel) {
        return new ResponseMessage<UserTokenModel>().prepareSuccessMessage(userService.saveModel(userModel));
    }

    @GetMapping("/{id}")
    public ResponseMessage<UserModel> getById(@PathVariable Long id) {
        return new ResponseMessage<UserModel>().prepareSuccessMessage(userService.getModelById(id));
    }

    @GetMapping()
    public List<UserModel> getAll() {
        return userService.getAllModel();
    }

    @GetMapping("/search-user")
    public Page<User> searchUser(@RequestBody UserSearch userSearch, Pageable pageable) {
        return userService.searchUser(userSearch, pageable);
    }

    @PostMapping("/sign-in")
    public ResponseMessage<UserTokenModel> signIn(@RequestBody UserAuthModel userAuthModel) {
        return new ResponseMessage<UserTokenModel>().prepareSuccessMessage(userService.getAuthorisationToken(userAuthModel));
    }

    @PutMapping()
    public ResponseMessage<UserModel> update(@RequestBody UserModel userModel) {
        return new ResponseMessage<UserModel>().prepareSuccessMessage(userService.updateModel(userModel));
    }

    @PutMapping("/update-image")
    public ResponseMessage<UserModel> setImage(@RequestParam(name = "file") MultipartFile multipartFile) {
        return new ResponseMessage<UserModel>().prepareSuccessMessage(userService.setImage(multipartFile));
    }

    @PutMapping("/update-name")
    public ResponseMessage<UserNameUpdate> updateName(@RequestBody UserNameUpdate userNameUpdate) {
        return new ResponseMessage<UserNameUpdate>().prepareSuccessMessage(userService.updateModel(userNameUpdate));
    }

    @PutMapping("/update-password")
    public ResponseMessage<UserUpdatePasswordModel> updatePassword(@RequestBody UserUpdatePasswordModel updatePasswordModel) {
        return new ResponseMessage<UserUpdatePasswordModel>().prepareSuccessMessage(userService.updateModel(updatePasswordModel));
    }
}
