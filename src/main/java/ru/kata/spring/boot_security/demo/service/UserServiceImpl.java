package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Query;
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
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void deletUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void addUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userDao.findUserByUsername(user.getUsername()) == null) {
            userDao.save(user);
        }
    }

    @Override
    @Transactional
    public void editUser(User user, Long id)  {
        System.out.println("edit in");
        if(userDao.findUserByUsername(user.getUsername()) == null
                 || userDao.findUserByUsername(user.getUsername()).equals(userDao.getById(id))) {
        userDao.findById(id)
                .ifPresent(u -> {u.setName(user.getName());
                                    u.setUsername(user.getUsername());
                                    u.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                                    u.setRoles(user.getRoles());});}

    }

}
