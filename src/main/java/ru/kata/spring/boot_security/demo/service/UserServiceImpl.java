package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private RoleDao roleDao;
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }
    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public void deletUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void editUser(User user, int id)  {

        userDao.findById(id)
                .ifPresent(u -> {u.setName(user.getName());
                                    u.setUsername(user.getUsername());
                                    u.setPassword(user.getPassword());});

    }
}
