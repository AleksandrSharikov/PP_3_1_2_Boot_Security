package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> getList() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }



    @PostMapping(path = "/")
    public ResponseEntity<User> create(@RequestBody User newUser ) {
        userService.addUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletUser(@PathVariable Long id) {
        userService.deletUser(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User editUser){
        userService.editUser(editUser,id);
        return new ResponseEntity<>(editUser, HttpStatus.OK);
    }
}
