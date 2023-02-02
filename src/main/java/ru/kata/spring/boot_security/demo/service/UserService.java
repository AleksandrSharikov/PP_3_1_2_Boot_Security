package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getById(int id);
    void deletUser(int id);
    void addUser(User user);
    void editUser(User user, int id);
    UserDetails loadUserByUsername(String username);
    int findIdByUsername(String username);
}
