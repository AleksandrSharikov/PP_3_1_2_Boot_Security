package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;

@Component
public class TableInitializer implements CommandLineRunner {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

    roleDao.save(new Role(1,"ROLE_USER"));
    roleDao.save(new Role(2,"ROLE_ADMIN"));
        System.out.println("Roles done");
    User admin = new User("admin","admin", "AnyName");
    admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
    admin.setRoles(Collections.singleton(new Role(2, "ROLE_ADMIN")));
    admin.setId(1);
    userDao.save(admin);
        System.out.println("Admin added");

    User user = new User("user","user", "AnyName");
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
    user.setId(2);
    userDao.save(user);
        System.out.println("User added");
    }
}
