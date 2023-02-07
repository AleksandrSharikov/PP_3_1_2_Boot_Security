package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getById(Long id);
    void deletUser(Long id);
    void addUser(User user);
    void editUser(User user, Long id);

}
