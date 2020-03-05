package com.litrc.votingsystem.vote.controller;

import com.litrc.votingsystem.vote.model.User;
import com.litrc.votingsystem.vote.payload.UserEmailUpdateForm;
import com.litrc.votingsystem.vote.payload.UserPasswordUpdateForm;
import com.litrc.votingsystem.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 获取指定用户名的用户信息
    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    // 更改邮箱
    @PutMapping("/email")
    public User updateUserEmail(@RequestParam String username,
                               @Valid @RequestBody UserEmailUpdateForm form) {
        return userService.updateEmail(username, form);
    }

    // 更改密码
    @PutMapping("/password")
    public User updateUserPassword(@RequestParam String username,
                                   @Valid @RequestBody UserPasswordUpdateForm form) {
        return userService.updatePassword(username, form);
    }

    // 注册
    @PutMapping("/register")
    public boolean register(@RequestBody User user) {
        userService.register(user);
        return true;
    }

//    // 登陆
//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//        return userService.login(user);
//    }

}
