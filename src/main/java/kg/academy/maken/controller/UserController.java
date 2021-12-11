package kg.academy.maken.controller;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.*;
import kg.academy.maken.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel userModel) {
        return userService.saveModel(userModel);
    }

    @GetMapping("/{id}")
    public UserModel getById(@PathVariable Long id) {
        return userService.getModelById(id);
    }

    @GetMapping()
    public List<UserModel> getAll() {
        return userService.getAllModel();
    }

    @GetMapping("/search-user")
    public Page<User> searchUser(@RequestBody UserSearch userSearch, Pageable pageable) {
        return userService.searchUser(userSearch, pageable);
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserAuthModel userAuthModel) {
        return userService.getAuthorisationToken(userAuthModel);
    }

    @PutMapping()
    public UserModel update(@RequestBody UserModel userModel) {
        return userService.updateModel(userModel);
    }

    @PutMapping("/update-name")
    public UserNameUpdate updateName(@RequestBody UserNameUpdate userNameUpdate) {
        return userService.updateModel(userNameUpdate);
    }

    @PutMapping("/update-password")
    public UserUpdatePasswordModel updatePassword(@RequestBody UserUpdatePasswordModel updatePasswordModel) {
        return userService.updateModel(updatePasswordModel);
    }
}
