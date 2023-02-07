package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;

@Component
public class TableInitializer implements ApplicationRunner {
   private final UserService userService;
    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
 public TableInitializer(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
  this.userService = userService;
  this.roleService = roleService;
  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
 }


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
