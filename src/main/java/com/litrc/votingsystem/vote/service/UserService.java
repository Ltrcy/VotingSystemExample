package com.litrc.votingsystem.vote.service;

import com.litrc.votingsystem.vote.dao.UserDAO;
import com.litrc.votingsystem.vote.exception.ResourceNotFoundException;
import com.litrc.votingsystem.vote.model.User;
import com.litrc.votingsystem.vote.payload.UserEmailUpdateForm;
import com.litrc.votingsystem.vote.payload.UserPasswordUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", username));
    }

    public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userDAO
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    public User updateEmail(String username, UserEmailUpdateForm form) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", username));
        user.setEmail(form.getEmail());
        return userDAO.save(user);
    }

    public User updatePassword(String username, UserPasswordUpdateForm form) {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> ResourceNotFoundException.build("User", "Username", username));
        user.setPassword(form.getPassword());
        return userDAO.save(user);
    }

    //注册
    public void register(User user) {
        userDAO.save(user);
    }

//    // 登陆
//    public String login(User loginUser) {
//        return userDAO
//                .findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword())
//                .map(Token::new)
//                .map(tokenDAO::saveAndFlush)
//                .map(Token::getTokenContent)
//                .orElseThrow(PasswordOrUsernameWrongException::new);
//    }


    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }
}
