package ru.kata.spring.boot_security.demo.service;

import com.springboot.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){this.userDao = userDao;}
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
                                    u.setSurname(user.getSurname());
                                    u.setAge(user.getAge());});

    }
}
