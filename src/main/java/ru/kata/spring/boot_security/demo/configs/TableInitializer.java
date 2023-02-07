package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;

@Component
public class TableInitializer implements ApplicationRunner {
   @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    roleService.addRole(new Role(1L,"ROLE_USER"));
    roleService.addRole(new Role(2L,"ROLE_ADMIN"));
        System.out.println("Roles done");


    User admin = new User("admin","admin", "AdminsName");
    admin.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
    admin.setId(1L);
    userService.addUser(admin);
        System.out.println("Admin added");

    User user = new User("user","user", "UsersName");
    user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
    user.setId(2L);
    userService.addUser(user);
        System.out.println("User added");


    }
}
