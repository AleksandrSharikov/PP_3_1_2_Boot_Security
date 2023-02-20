package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

   @RequestMapping(value = "/main")
   public ModelAndView mainPage(){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("/admin/main");
       return modelAndView;
    }

    @RequestMapping(value = "/userForm")
    public ModelAndView userForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/newForm");
        return modelAndView;
    }

    @GetMapping("/")
    public List<User> getList() {
        System.out.println("Get list");
        return userService.getUserList();
    }
    //            consumes = MediaType.APPLICATION_JSON_VALUE,
    //            produces = MediaType.APPLICATION_JSON_VALUE

    @PostMapping(path = "/")
    public void create(@RequestBody User newUser) {
        System.out.println("create");
        userService.addUser(newUser);
    }

    @DeleteMapping(path = "/{id}")
    public String deletUser(@PathVariable Long id) {
        userService.deletUser(id);
        System.out.println("Delete");
        return "Deleted";
    }



}
