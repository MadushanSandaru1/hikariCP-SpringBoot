package com.example.hikari.services;

import com.example.hikari.beans.User;
import com.example.hikari.dao.UserDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.getUsers();
    }

    public User getUserById(Integer id) {
        return userDAO.getUserById(id);
    }

}
