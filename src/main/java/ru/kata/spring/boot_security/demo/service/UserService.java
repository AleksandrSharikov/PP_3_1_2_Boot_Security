package ru.kata.spring.boot_security.demo.service;

import com.springboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getById(int id);
    void deletUser(int id);
    void addUser(User user);
    void editUser(User user, int id);
}
