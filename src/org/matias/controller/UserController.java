package org.matias.controller;

import org.matias.model.User;
import org.matias.model.UserDao;

import java.util.List;

public class UserController {
    public void insertUser(User user) {
        UserDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return UserDao.getAllUsers();
    }

    public List<User> searchUsersByName(String name) {
        return UserDao.searchUsersByName(name);
    }

    public void updateUser(User user) {
        UserDao.updateUser(user);
    }

    public void deleteUser(int userId) {
        UserDao.deleteUser(userId);
    }
}
