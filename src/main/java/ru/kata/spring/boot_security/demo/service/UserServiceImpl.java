package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void editUser(User user, int id)  {

        userDao.findById(id)
                .ifPresent(u -> {u.setName(user.getName());
                                    u.setUsername(user.getUsername());
                                    u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));});

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username);
    }
    public int findIdByUsername(String username){
        return userDao.findUserByUsername(username).getId();
    }

}
