package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

   @RequestMapping(value = "/main")
   public ModelAndView mainPage(){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("/admin/main");
       return modelAndView;
    }

    @RequestMapping(value = "/form")
    public ModelAndView userForm(){
        System.out.println("UserForm");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/newUser");
        return modelAndView;
    }


    // Edit form
    @RequestMapping(value = "/{id}")
    public ModelAndView userForm(@PathVariable Long id){
        System.out.println("EditForm");
        ModelAndView modelAndView2 = new ModelAndView();
        modelAndView2.setViewName("/admin/editUser");
        return modelAndView2;
    }

    @GetMapping("/")
    public List<User> getList() {
        System.out.println("Get list");
        return userService.getUserList();
    }



    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public User create(@RequestBody User newUser ) {
        System.out.println(newUser);
        userService.addUser(newUser);
        System.out.println(newUser);
        return newUser;
    }

    @DeleteMapping(path = "/{id}")
    public String deletUser(@PathVariable Long id) {
        userService.deletUser(id);
        System.out.println("Delete");
        return "Deleted";
    }
    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public User editUser(@PathVariable Long id, @RequestBody User editUser){
        userService.editUser(editUser,id);
        return editUser;
    }


}
